package com.example.bubblenote.repo

import com.example.bubblenote.model.Note
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddNoteRepo {
    lateinit var email:String
    var db = Firebase.firestore
    fun addNote(
        note: Note, s: () -> Unit,
        f: (Any) -> Unit
    ) {
        var classObject = HashMap<String, String>()

        NoteRepo().userMail {
            email = it
        }
        classObject["Email"] = email
        classObject["Title"] = note.title
        classObject["Content"] = note.content


        db.collection("Note").document(email)
            .set(classObject)
            .addOnSuccessListener { documentReference ->
                s.invoke()

            }.addOnFailureListener { e ->
                f.invoke(e)
            }
    }

}