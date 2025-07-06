package com.example.week2.day1_architecture.model

class UserRepository {
    fun getUsers(): List<User> {
        return users.toList()
    }

    fun fetchUsers(): List<User> {
        // fetch Users from network.
        return emptyList<User>()
    }

}