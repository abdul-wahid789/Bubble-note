package com.example.bubblenote.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.bubblenote.R
import com.example.bubblenote.databinding.ActivityNoteViewBinding
import com.example.bubblenote.model.Note
import com.example.bubblenote.showToastMessage
import com.example.bubblenote.viewmodel.NoteViewVM

class NoteViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityNoteViewBinding
    val vm: NoteViewVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteViewBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        binding.saveBtn.setOnClickListener {
            var title = binding.title.text.toString()
            var content = binding.content.text.toString()
            vm.addNote(Note(title, content), {
                showToastMessage(this, it)
            }, { e: Any, m: String ->
                showToastMessage(this, e.toString() + " :" + m)
            })
        }
    }
}