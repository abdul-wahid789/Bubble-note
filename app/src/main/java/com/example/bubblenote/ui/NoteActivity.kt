package com.example.bubblenote.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.activity.viewModels
import com.example.bubblenote.databinding.ActivityNoteBinding
import com.example.bubblenote.repo.NoteRepo
import com.example.bubblenote.viewmodel.NoteVM
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding
    private lateinit var auth: FirebaseAuth
    val vm:NoteVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        vm.userEmail {
            binding.name.text=it
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
            finish()
        }
    }
}