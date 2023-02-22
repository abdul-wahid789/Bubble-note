package com.example.bubblenote.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.bubblenote.NoteViewAdapter
import com.example.bubblenote.databinding.ActivityNoteBinding
import com.example.bubblenote.model.Note
import com.example.bubblenote.showToastMessage
import com.example.bubblenote.viewmodel.NoteVM
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding
    val vm: NoteVM by viewModels()
    var list: MutableList<Note> = mutableListOf()
    lateinit var adapter: NoteViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        vm.userEmail {
            binding.name.text = it
        }

        //adding data into list
        var db = Firebase.firestore
        lateinit var email: String
        vm.userEmail {
            email = it
        }
        val docRef = db.collection("Note").document(email)
        db.collection("Note")
            .whereEqualTo("Email", email)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    list.add(
                        Note(
                            document.data["Title"].toString(),
                            document.data["Content"].toString()
                        )
                    )
                    //Log.d(TAG, "${document.id} => ${document.data}")
                }
                adapter = NoteViewAdapter(list)
                binding.recView.adapter = adapter
                adapter.notifyDataSetChanged()


            }
            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents: ", exception)
                showToastMessage(this, "ERROR")
            }





        binding.logOut.setOnClickListener {
            Firebase.auth.signOut()
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.addNoteBtn.setOnClickListener {
            intent = Intent(this, NoteViewActivity::class.java)
            startActivity(intent)
        }
    }

}