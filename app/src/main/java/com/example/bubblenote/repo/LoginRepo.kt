package com.example.bubblenote.repo

import android.content.Intent
import android.util.Log
import android.view.View
import com.example.bubblenote.showToastMessage
import com.example.bubblenote.ui.NoteActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginRepo {
    private lateinit var auth: FirebaseAuth
    fun loginUser(
        email: String,
        password: String,
        s: () -> Unit,
        f: (Any) -> Unit
    ) {
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    s.invoke()
                } else {
                    f.invoke(task.exception!!)

                }
            }
    }
}