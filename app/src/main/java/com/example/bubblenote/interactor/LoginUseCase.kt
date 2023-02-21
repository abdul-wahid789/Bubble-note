package com.example.bubblenote.interactor

import com.example.bubblenote.repo.LoginRepo

class LoginUseCase {
    fun loginUser(
        email: String,
        password: String,
        s: (String) -> Unit,
        f: (Any, String) -> Unit,
        empty: (String) -> Unit
    ) {
        if (email.isEmpty() or password.isEmpty()) {
            empty.invoke("Fill up")
        } else {
            LoginRepo().loginUser(email, password, {
                s.invoke("Login Success")
            }, {
                f.invoke(it, "Login Failed")
            })
        }
    }
}