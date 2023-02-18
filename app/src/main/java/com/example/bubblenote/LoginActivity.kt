package com.example.bubblenote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.bubblenote.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.loginBtn.setOnClickListener {
            var email: String = binding.email.text.toString()
            var password: String = binding.password.text.toString()
            if (email.isNotEmpty() and password.isNotEmpty()) {
                binding.loading.visibility= View.VISIBLE
                auth = Firebase.auth
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            binding.loading.visibility= View.GONE
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Login", "signInWithEmail:success")
                            showToastMessage(this,"Success")
                            val user = auth.currentUser
//                        updateUI(user)
                            intent = Intent(this, NoteActivity::class.java)
                            startActivity(intent)
                        } else {
                            binding.loading.visibility= View.GONE
                            // If sign in fails, display a message to the user.
                            Log.w("Login", "signInWithEmail:failure", task.exception)
                            showToastMessage(this,"Wrong Email or Password")
                        }
                    }
            }
            binding.signUp.setOnClickListener {
                intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}