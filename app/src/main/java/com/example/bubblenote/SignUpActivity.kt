package com.example.bubblenote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.bubblenote.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    private val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        binding.signUpBtn.setOnClickListener {
            if (binding.password.text.toString() == binding.comfirmPassword.text.toString()) {
                binding.loading.visibility = View.VISIBLE
                auth.createUserWithEmailAndPassword(
                    binding.email.text.toString(),
                    binding.password.text.toString()
                ).addOnCompleteListener(this) { Task ->
                    Log.d("Email", binding.email.toString())
                    if (Task.isSuccessful) {
                        //adding other information in Firestore
                        var classObject = HashMap<String, String>()
                        classObject["First Name"] = binding.firstName.text.toString()
                        classObject["Last Name"] = binding.lasttName.text.toString()
                        classObject["Email"] = binding.email.text.toString()
                        classObject["Password"] = binding.password.text.toString()

                        db.collection("User").document(binding.email.text.toString())
                            .set(classObject)
                            .addOnSuccessListener { documentReference ->
                                showToastMessage(this, "Registration success")
                                binding.loading.visibility = View.GONE

                            }
                            .addOnFailureListener { e -> //Firestore
                                showToastMessage(this, e.message.toString())
                                binding.loading.visibility = View.GONE
                            }
                        //end


                        val user = auth.currentUser
                        binding.loading.visibility = View.GONE
                        recreate()

                    } else {  //Authentication else
                        Log.w("Registration", Task.exception!!.message.toString())
                        Toast.makeText(
                            this,
                            Task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.loading.visibility = View.GONE
                    }
                }
            } else {
                binding.comfirmPassword.error = "Password not match"
            }
            binding.logIn.setOnClickListener {
                intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        binding.logIn.setOnClickListener {
            intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}