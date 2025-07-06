package com.example.week6.day1_day2_room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject
constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
//    private val _users = MutableStateFlow<List<User>>(emptyList())
//    val users: StateFlow<List<User>> get() = _users.asStateFlow()

    val users = userRepository.getUserAndAadhaarCard()
//     val users = emptyFlow<List<User>>()

    fun insertUser(
        id: Long? = null,  // for first time insertion id will be null, second time have the value.
        username: String,
        email: String,
        fullName: String,
        city: String,
        aadhaarId: String
    ) {
        viewModelScope.launch(Dispatchers.IO){
            if (id == null) {  // this is for Insert new user
               val userId =  userRepository.insertUser(username, email, fullName, Address(city), Instant.now()) // here , we delegate the task of inserting user to the repository.
                userRepository.insertAadhaarCard(AadhaarCard(id = aadhaarId, userId = userId))
            }                                               //  Database operations (like fetching all users from Room) should be performed off the main thread to avoid blocking the UI and causing Application Not Responding (ANR) errors.
            else {  // this is for the update user.
                val user = User(id, username, email, fullName, Address(city), Instant.now())
                userRepository.updateUser(user)
            }
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user)
        }
    }

//    fun getUserAndAadhaarCard(): List<UserAndAadhaarCard> {
//        return userRepository.getUserAndAadhaarCard()
//    }



//    fun getAllUsers() {   //  Coroutines launched with viewModelScope (often in conjunction with a Dispatcher like Dispatchers.IO for database/network calls, though Room handles this internally for suspend functions) allow these operations to run asynchronously.
//        viewModelScope.launch(Dispatchers.IO) {
//            val users = userRepository.getAllUsers()
//            _users.value = users
//        }
//    }
}
