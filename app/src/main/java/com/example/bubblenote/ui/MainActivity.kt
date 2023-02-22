package com.example.bubblenote.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bubblenote.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginBtn.setOnClickListener{
            intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.signUpBtn.setOnClickListener{
            intent= Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
    public override fun onStart() {
        super.onStart()
        val currentUser = Firebase.auth.currentUser
        if(currentUser != null){
            currentUser?.let {
                intent=Intent(this, NoteActivity::class.java)
                startActivity(intent)
                finish()

            }
        }
    }
}