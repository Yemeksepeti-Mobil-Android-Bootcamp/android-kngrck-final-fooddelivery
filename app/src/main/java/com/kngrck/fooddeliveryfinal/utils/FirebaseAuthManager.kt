package com.kngrck.fooddeliveryfinal.utils

import android.content.Context
import android.util.Log
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
    fun initialize(context: Context) {
        auth = Firebase.auth
        currentUser = auth.currentUser
    }

    fun signIn(listener: AuthListener) {
        auth.signInWithEmailAndPassword("test@test.com", "123123").addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("AUTH", "Success")
                listener.isAuthSuccess(true)
            } else {
                Log.d("AUTH", "fail")
                listener.isAuthSuccess(false)
            }

        }

    }

    fun signOut() {
        auth.signOut()
    }

    fun getCurrentUser(): FirebaseUser? = currentUser


}
