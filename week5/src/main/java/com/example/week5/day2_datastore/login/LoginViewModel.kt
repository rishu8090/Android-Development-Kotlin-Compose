package com.example.week5.day2_datastore.login

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5.day2_datastore.datastore.DataStoreRepository     // by default, this is loaded on the main thread, that's why we use Dispatchers.IO, and move the work to IO thread.
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    fun login() {     // if we mark this as suspend fun and call from UI, and we call it from a coroutine scope in an activity (like lifecycleScope.launch).
        viewModelScope.launch(Dispatchers.IO) {
//            delay(5.seconds) // simulate login operation
            // perform login
            // save the user is authenticated info in datastore
            dataStoreRepository.saveUserAuthenticated(authenticated = true)
        }
    }
}