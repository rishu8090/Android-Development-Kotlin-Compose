package com.example.week5.day2_datastore.home

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week5.day2_datastore.datastore.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
     fun logout(){
         viewModelScope.launch(Dispatchers.IO) {  // by default, this is loaded on the main thread, that's why we use Dispatchers.IO, and move the work to IO thread.
//             delay(5.seconds)
             dataStoreRepository.logout()
         }
     }
}