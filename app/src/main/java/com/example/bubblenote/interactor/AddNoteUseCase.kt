package com.example.bubblenote.interactor

import com.example.bubblenote.model.Note
import com.example.bubblenote.repo.AddNoteRepo

class AddNoteUseCase {
    fun addNote(note: Note, s: (String) -> Unit, f: (Any, String) -> Unit) {
        AddNoteRepo().addNote(note, {
            s.invoke("Note save")
        }, {

            f.invoke(it, "Failed")

        })
    }
}