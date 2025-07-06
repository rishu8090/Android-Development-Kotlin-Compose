package com.example.week2.day1_architecture.viewModel
import androidx.lifecycle.ViewModel
import com.example.week2.day1_architecture.model.User
import com.example.week2.day1_architecture.model.UserRepository
import com.example.week2.day1_architecture.model.users
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// it is better way to use ViewModel in project, and you should have to use it.
class GoogleViewModel : ViewModel() {   // by using viewModel by this way , it survive the configuration changes. like screen rotation.
    val userRepository = UserRepository()

    private val _usersFlow = MutableStateFlow<List<User>>(emptyList())  // for writing data
    val userFlow: StateFlow<List<User>> = _usersFlow.asStateFlow()

    var currentId = 1
    fun addUser(){
        val user = User(currentId++, "Rishu")
        users.add(user)
//        usersFlow.value = users
//        users.forEach { Log.d("TAG", "User: $it") }
        _usersFlow.value = userRepository.getUsers()

    }
}