package com.example.bubblenote.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bubblenote.interactor.LoginUseCase

class LoginVM : ViewModel() {
    fun loginUser(
        email: String,
        pass: String,
        s: (String) -> Unit,
        f: (Any, String) -> Unit,
        empty: (String) -> Unit
    ) {
        LoginUseCase().loginUser(email, pass, {
            s.invoke(it)
        }, { e: Any, m: String ->
            f.invoke(e, m)
        }, {
            empty.invoke(it)
        })
    }
}