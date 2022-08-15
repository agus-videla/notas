package com.example.notas

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notas.model.Note

class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val noteTitle = view.findViewById<TextView>(R.id.tvCardNoteTitle)
    val noteBody = view.findViewById<TextView>(R.id.tvCardNoteBody)

    fun render(noteModel : Note) {
        noteTitle.text = noteModel.title
        noteBody.text = noteModel.body
    }
}