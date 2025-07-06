package com.example.week2.day2_coroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CoroutineViewModel: ViewModel() {
    init {
        viewModelScope.launch {
            GlobalScope.launch { }  // if a GlobalScope is diffend in a viewModel scope and this viewModel scope is cancelled, than in this case GlobalScope will also be cancelled
                                        // doesn't matter if the viewModel scope is cancelled or not. bcz GlobalScope has its scope not to parent but through the application/ app.
        }
    }

    fun test(){
        viewModelScope.launch(Dispatchers.IO) {
            // call test function from repository  // viewModelScope is used when we call from viewModel or Repository or any model like places.
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}