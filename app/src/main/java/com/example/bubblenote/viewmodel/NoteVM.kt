package com.example.bubblenote.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bubblenote.interactor.NoteUseCase

class NoteVM : ViewModel(){
    fun userEmail(s:(String)->Unit){
        NoteUseCase().userEmail {
            s.invoke(it)
        }
    }
}