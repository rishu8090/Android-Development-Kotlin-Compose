package com.example.week9.home

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    fun logDetailScreenViewEvent() {
        homeRepository.logDetailScreenViewEvent()
    }
}