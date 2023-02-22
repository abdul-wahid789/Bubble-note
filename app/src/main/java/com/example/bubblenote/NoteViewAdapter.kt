package com.example.bubblenote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bubblenote.databinding.ActivityNoteBinding
import com.example.bubblenote.databinding.ActivityNoteViewBinding
import com.example.bubblenote.databinding.ItemContainerBinding
import com.example.bubblenote.model.Note

class NoteViewAdapter(var list: List<Note>) :
    RecyclerView.Adapter<NoteViewAdapter.NoteViewHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bindView(list, position)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemContainerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class NoteViewHolder(private val binding: ItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(list: List<Note>, position: Int) {
            binding.title.text = list[position].title
            binding.content.text = list[position].content
        }
    }


}