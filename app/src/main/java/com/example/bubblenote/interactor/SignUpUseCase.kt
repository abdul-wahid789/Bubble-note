package com.example.bubblenote.interactor

import com.example.bubblenote.model.UserInfo
import com.example.bubblenote.repo.SignUpRepo

class SignUpUseCase {
    fun addUserToAuthenticate(
        email: String,
        pass: String,
        success: () -> Unit,
        failed: (error: Any, msg: String) -> Unit
    ) {
        SignUpRepo().addUserToAuthenticate(
            email,
            pass, {
                success.invoke()
            }, { error: Any ->
                failed.invoke(error, "Authentication")
            })
    }

    fun addUser(
        userInfo: UserInfo,
        success: (String) -> Unit,
        failed: (error: Any, msg: String) -> Unit

    ) {
        SignUpRepo().saveUser(userInfo, {
            success.invoke("Registration successful")
        },
            {
                failed.invoke(it, "Firestore")
            })
    }
    fun checkPass(pass1: String, pass2: String,f:(String)->Unit,s:()->Unit) {
        if(pass1!=pass2){
            f.invoke("Password Not Match")
        }else{
            s.invoke()
        }
    }

}