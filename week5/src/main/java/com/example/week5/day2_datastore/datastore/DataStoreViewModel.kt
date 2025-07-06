package com.example.week5.day2_datastore.datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): ViewModel(){
    // in viewModel we usually use stateflow, bcz stateflow is stateHolder, it contains only one value at a time.
    val authenticated: StateFlow<Boolean > =  dataStoreRepository.authenticated.stateIn(   // to change flow to stateflow we use stateIn()
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )
}