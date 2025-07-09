package com.example.week9.authentication

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

interface AuthRepository {
    fun signUp(email: String, password: String)
    fun signIn(email: String, password: String)
}

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    override fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
    }

    override fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }
}