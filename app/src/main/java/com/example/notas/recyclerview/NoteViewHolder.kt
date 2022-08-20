package com.example.notas.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notas.databinding.RecyclerViewRowBinding
import com.example.notas.model.Note

class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = RecyclerViewRowBinding.bind(view)

    fun render(noteModel: Note, onClickListener: (Int) -> Unit, onClickDelete: (Int) -> Unit) {
        binding.tvCardNoteTitle.text = noteModel.title
        binding.tvCardNoteBody.text = noteModel.body
        binding.cardView.setOnClickListener {
            onClickListener(adapterPosition)
        }
        binding.btnDelete.setOnClickListener {
            onClickDelete(adapterPosition)
        }
    }
}