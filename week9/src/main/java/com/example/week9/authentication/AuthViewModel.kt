package com.example.week9.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week9.datastore.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Initial)
    val authState: StateFlow<AuthState> get() = _authState.asStateFlow()

    init{
        Log.d("TAG", "Created a instance of ${this::class.simpleName}")
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _authState.value = AuthState.Loading
            delay(2.seconds)  // to view the loading indicator.
            authRepository.signUp(
                email = email,
                password = password,
                onSuccess = {
                    saveIsAuthenticated(true)
                    _authState.value = AuthState.Success
                },
                onFailure = { exception ->
                    _authState.value = AuthState.Error(exception.message ?: "Unknown error")
                }
            )
        }
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _authState.value = AuthState.Loading
            delay(2.seconds)  // to view the loading indicator.
            authRepository.signIn(
                email = email,
                password = password,
                onSuccess = {
                    saveIsAuthenticated(true)
                    _authState.value = AuthState.Success
                },
                onFailure = { exception ->
                    _authState.value = AuthState.Error(exception.message ?: "Unknown error")
                })
        }
    }

    fun saveIsAuthenticated(authenticated: Boolean) {
        viewModelScope.launch {
            dataStoreRepository.saveIsAuthenticated(authenticated)
        }
    }

    override fun onCleared(){
        Log.d("TAG", "Clearing an instance of ${this::class.simpleName }")
    }
}

sealed interface AuthState {
    data object Initial : AuthState
    data object Loading : AuthState
    data object Success : AuthState
    data class Error(val message: String) : AuthState
}