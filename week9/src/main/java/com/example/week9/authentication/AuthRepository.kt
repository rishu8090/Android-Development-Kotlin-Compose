package com.example.week9.authentication

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface AuthRepository {
    fun signUp(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

    fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    )

    fun signOut()
}

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {
    @OptIn(DelicateCoroutinesApi::class)
    override fun signUp(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                authResult.user?.let {
                    GlobalScope.launch {
                        saveToFirestore(it)  // save data on the firestore when a new signUp happens.
                        Log.d("TAG", "authResult: $authResult")
                        onSuccess()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "exception: $exception")
                onFailure(exception)
            }
    }

    override fun signIn(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                Log.d("TAG", "authResult: $authResult")
                onSuccess()
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "exception: $exception")
                onFailure(exception)
            }
    }

    suspend fun saveToFirestore(user: FirebaseUser){
       val usersCollection =  firestore.collection("users")
//        val usersReference = usersCollection.document()
//            .get()
//            .await()    // this block of code is used to check if the user is already registered or not.
//
//        if(usersReference.exists()){
//        Log.d("TAG", "User ${user.uid} already exists.")
// Week_9_Day_2 - Firebase Authentication & Firestore           return
//        }
        usersCollection.document(user.uid)
            .set(user)  // Returns:  A Task that will be resolved when the write finishes.
            .await() // Awaits the completion of the task without blocking a thread.
    }

    override fun signOut() {
        auth.signOut()  // inbuilt fun of firebase to sign out.
    }
}