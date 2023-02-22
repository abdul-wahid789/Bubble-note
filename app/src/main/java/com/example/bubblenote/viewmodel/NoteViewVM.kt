package com.example.bubblenote.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bubblenote.interactor.AddNoteUseCase
import com.example.bubblenote.model.Note

class NoteViewVM : ViewModel() {
    fun addNote(note: Note, s: (String) -> Unit, f: (Any, String) -> Unit) {
        AddNoteUseCase().addNote(note, {
            s.invoke(it)
        }, { e: Any, m: String ->
            f.invoke(e, m)
        })
    }
}