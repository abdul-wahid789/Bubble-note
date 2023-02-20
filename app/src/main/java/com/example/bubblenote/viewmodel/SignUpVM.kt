package com.example.bubblenote.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bubblenote.interactor.SignUpUseCase
import com.example.bubblenote.model.UserInfo

class SignUpVM : ViewModel() {

    fun checkPass(pass1: String, pass2: String,f:(String)->Unit,s:()->Unit) {
        SignUpUseCase().checkPass(pass1, pass2,{ f.invoke(it) },{ s.invoke()})
    }

    fun saveUserInAuthenticate(
        email: String,
        pass: String,
        success: () -> Unit,
        failed: (error: Any, msg: String) -> Unit
    ) {
        SignUpUseCase().addUserToAuthenticate(
            email,
            pass,
            {
                success.invoke()
            },
            { it: Any, msg: String ->
                failed.invoke(it, msg)
            })
    }

    fun saveUser(
        userInfo: UserInfo,
        success: (String) -> Unit,
        failed: (error: Any, msg: String) -> Unit
    ) {
        SignUpUseCase().addUser(userInfo, {
            success.invoke(it)
        },
            { e: Any, m: String ->
                failed(e, m)
            })
    }


}