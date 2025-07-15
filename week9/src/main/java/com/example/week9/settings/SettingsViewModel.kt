package com.example.week9.settings

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week9.authentication.AuthRepository
import com.example.week9.datastore.DataStoreRepository
import com.example.week9.navigation.NavigationDestination
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _startDestination =
        MutableStateFlow<NavigationDestination>(NavigationDestination.Splash)
    val startDestination: StateFlow<NavigationDestination> get() = _startDestination

    init {
        viewModelScope.launch {
            delay(2.seconds)  // to view the Splash Screen.
            dataStoreRepository.authenticated.collect { authenticated ->
                _startDestination.value = if (authenticated) {
                    NavigationDestination.Home
                } else {
                    NavigationDestination.SignIn
                }
            }
        }
    }

    val authenticated = dataStoreRepository.authenticated.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )


    fun saveIsAuthenticated(authenticated: Boolean) {
        viewModelScope.launch {
            dataStoreRepository.saveIsAuthenticated(authenticated)
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.signOut()   // this will logout to the firebase.
            dataStoreRepository.saveIsAuthenticated(false) // this changes the share prefs, by which next time user will be redirected to SignIn Screen.
        }
    }
}