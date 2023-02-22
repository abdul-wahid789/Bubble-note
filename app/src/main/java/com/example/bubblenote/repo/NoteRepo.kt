package com.example.bubblenote.repo

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NoteRepo {
    var db=Firebase.firestore
    fun userMail(s: (String) -> Unit) {
        val user = Firebase.auth.currentUser
        if (user != null) {
            s.invoke(user.email.toString())
        } else {
            s.invoke("")
        }
    }
    fun getData(){

    }
}