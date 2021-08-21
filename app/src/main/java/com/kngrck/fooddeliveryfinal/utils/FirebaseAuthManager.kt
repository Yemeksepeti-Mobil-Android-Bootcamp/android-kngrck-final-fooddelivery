package com.kngrck.fooddeliveryfinal.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

interface AuthListener {
    fun isAuthSuccess(success: Boolean)
}

object FirebaseAuthManager {
    private lateinit var auth: FirebaseAuth
    private var currentUser: FirebaseUser? = null
    fun initialize() {
        auth = Firebase.auth
        currentUser = auth.currentUser
    }

    fun signIn(email: String, password: String, listener: AuthListener) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                listener.isAuthSuccess(true)
            } else {
                listener.isAuthSuccess(false)
            }

        }

    }

    fun signUp(email: String, password: String, listener: AuthListener) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                listener.isAuthSuccess(true)
            } else {
                listener.isAuthSuccess(false)
            }

        }
    }

    fun signOut() {
        auth.signOut()
    }

    fun getCurrentUser(): FirebaseUser? = auth.currentUser


}
