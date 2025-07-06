package com.example.week4.day3_hilt

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// Constructor Injection.
@HiltViewModel
class DIViewModel @Inject constructor(
     val repository: DIRepository,
     val anotherDIRepository: AnotherDIRepository
 ): ViewModel(){
 fun doSomething(){
     println(" DIViewModel: doSomething")
 }
 }