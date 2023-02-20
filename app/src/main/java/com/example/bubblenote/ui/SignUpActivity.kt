package com.example.bubblenote.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.bubblenote.databinding.ActivitySignUpBinding
import com.example.bubblenote.model.UserInfo
import com.example.bubblenote.showToastMessage
import com.example.bubblenote.viewmodel.SignUpVM
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    val vm: SignUpVM by viewModels()
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.signUpBtn.setOnClickListener {
            var firstName: String = binding.firstName.text.toString()
            var lastName: String = binding.lastName.text.toString()
            var email: String = binding.email.text.toString()
            var pass1: String = binding.password.text.toString()
            var pass2: String = binding.comfirmPassword.text.toString()

            vm.checkPass(pass1, pass2, {
                binding.password.error = it
            }, {
                binding.loading.visibility = View.VISIBLE
                vm.saveUserInAuthenticate(email, pass1, {
                    vm.saveUser(UserInfo(firstName, lastName, email, pass1), {
                        showToastMessage(this, it)
                        binding.loading.visibility = View.GONE
                    }, { error, msg ->
                        failedMsg(error, msg)
                    })
                }, { it: Any, msg: String ->
                    failedMsg(it, msg)
                })

            })
        }
        binding.logIn.setOnClickListener {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun failedMsg(e: Any, msg: String) {
        showToastMessage(this, e.toString() + ": " + msg)
        binding.loading.visibility = View.GONE
    }
}
