package com.example.week2.day1_architecture.viewModel

import com.example.week2.day1_architecture.model.User
import com.example.week2.day1_architecture.model.UserRepository
import com.example.week2.day1_architecture.model.UserUseCase
import com.example.week2.day1_architecture.model.users
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModel {
    val userRepository = UserRepository()

   private val _usersFlow = MutableStateFlow<List<User>>(emptyList())  // for writing data
    val userFlow: StateFlow<List<User>> = _usersFlow.asStateFlow()  // for reading data  // here it is a observable. like liveData used to observe data, and it will notify whenever a change is happened in data.
/// here _usersFlow is observed by the usersFlow statFlow , whenever a change is happened in _usersFlow, usersFlow will be notified.

    var currentId = 1
    fun addUser(){
        val user = User(currentId++, "Rishu")
        users.add(user)
//        usersFlow.value = users
//        users.forEach { Log.d("TAG", "User: $it") }
        _usersFlow.value = userRepository.getUsers()

    }

    fun fetchUsers(){   // in this way, UserRepository helps to fetch data from network and increase reusability of the code.
        userRepository.fetchUsers()
    }
}


class ProfileViewModel(){
    val userUseCase = UserUseCase()
    val userRepository = UserRepository()
    fun fetchUser(){
        // fetch user from network
        userUseCase.getUsers()
    }
}
