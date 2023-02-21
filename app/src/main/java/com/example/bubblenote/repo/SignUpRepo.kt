package com.example.bubblenote.repo


import com.example.bubblenote.model.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpRepo {
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

    fun addUserToAuthenticate(
        email: String,
        pass: String,
        success: () -> Unit,
        failed: (error: Any) -> Unit
    ) {
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    success.invoke()
                } else {
                    failed.invoke(task.exception!!)
                }
            }
    }

    fun saveUser(
        userInfo: UserInfo,
        success: () -> Unit,
        failed: (error: Any) -> Unit
    ) {

        var classObject = HashMap<String, String>()

        classObject["First Name"] = userInfo.firstName
        classObject["Last Name"] = userInfo.lastName
        classObject["Email"] = userInfo.email
        classObject["Password"] = userInfo.pass

        db.collection("User").document(userInfo.email)
            .set(classObject)
            .addOnSuccessListener { documentReference ->
                val user = auth.currentUser
                success.invoke()

            }.addOnFailureListener { e ->
                failed.invoke(e)
            }
    }

}


