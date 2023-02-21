package com.example.bubblenote.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.bubblenote.databinding.ActivityLoginBinding
import com.example.bubblenote.showToastMessage
import com.example.bubblenote.viewmodel.LoginVM
import com.example.bubblenote.viewmodel.SignUpVM
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    val vm: LoginVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.loginBtn.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            var email: String = binding.email.text.toString()
            var password: String = binding.password.text.toString()
            vm.loginUser(email, password, {
                showToastMessage(this, it)
                intent = Intent(this, NoteActivity::class.java)
                startActivity(intent)
                binding.loading.visibility = View.GONE

            }, { e: Any, m: String ->
                showToastMessage(this, m + ": " + e)
                binding.loading.visibility = View.GONE

            }, {
                showToastMessage(this, it)
                binding.loading.visibility = View.GONE

            })
            binding.signUp.setOnClickListener {
                intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}