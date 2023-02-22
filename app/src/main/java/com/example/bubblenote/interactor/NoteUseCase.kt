package com.example.bubblenote.interactor

import com.example.bubblenote.repo.AddNoteRepo
import com.example.bubblenote.repo.NoteRepo

class NoteUseCase {
    fun userEmail(s: (String) -> Unit) {
        NoteRepo().userMail({
            s.invoke(it)
        })
    }
}