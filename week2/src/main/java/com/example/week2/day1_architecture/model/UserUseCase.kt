package com.example.week2.day1_architecture.model

class UserUseCase {
    val userRepository = UserRepository()

    fun getUsers(): List<User> {
        return userRepository.getUsers().map{ it }

    }
}