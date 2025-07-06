package com.example.week8.day1_workmanager

import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel

@HiltViewModel
class BackgroundWorkViewModel @Inject constructor(
    private val repository: BackgroundWorkRepository
) : ViewModel() {
    fun createOneTimeWorkRequest(){
        repository.createOneTimeWorkRequest()   // delegate the work to repo.
    }

    fun createOneTimeWorkRequestWithDataAndConstraints(){
        repository.createOneTimeWorkRequestWithDataAndConstraints()
    }

    fun createPeriodicWorkRequest(){
        repository.createPeriodicWorkRequest()
    }

}