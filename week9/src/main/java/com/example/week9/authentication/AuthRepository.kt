package com.example.week9.authentication

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date
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
    private val auth: FirebaseAuth
) : AuthRepository {
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
                        saveUserToFirestore(it)  // save data on the firestore when a new signUp happens.
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

//    suspend fun saveUserToFirestore(user: FirebaseUser){
//       val usersCollection = firestore.collection("users")
//        val usersReference = usersCollection.document(user.uid)     // this way, should work, but not working, Leftover to resolve.
//            .get()
//            .await()    // this block of code is used to check if the user is already registered or not.
//
//        if(usersReference.exists()){
//        Log.d("TAG", "User ${user.uid} already exists.")
//            return
//        }
//        usersCollection.document(user.uid)
//            .set(user)  // Returns:  A Task that will be resolved when the write finishes.
//            .await() // Awaits the completion of the task without blocking a thread.
//    }

    private fun saveUserToFirestore(user: FirebaseUser) {
        val firebaseAuth = FirebaseAuth.getInstance()
        val firestore = FirebaseFirestore.getInstance()

        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            // 2. Create an instance of your custom User class
            val userToSave = User(
                uid = firebaseUser.uid,
                displayName = firebaseUser.displayName,
                email = firebaseUser.email,
                photoUrl = firebaseUser.photoUrl?.toString() // Convert Uri to String if needed
                // createdAt will be set by @ServerTimestamp
            )

            // 3. Save your custom User object to Firestore
            firestore.collection("users")
                .document(firebaseUser.uid)
                .set(userToSave)
                .addOnSuccessListener {
                    Log.d("Firestore", "User data successfully written!")
                }
                .addOnFailureListener { e ->
                    Log.e("FirestoreError", "Error writing user data", e)
                }
        } else {
            Log.w("Firestore", "No user logged in, cannot save user data.")
        }
    }

    data class User(
        val uid: String = "",   // Step 1. Create a costume data class, which we will be stored on firestore.
        val displayName: String? = null,
        val email: String? = null,
        val photoUrl: String? = null,
        @ServerTimestamp
        val createdAt: Date? = null
    )

    override fun signOut() {
        auth.signOut()  // inbuilt fun of firebase to sign out.
    }
}