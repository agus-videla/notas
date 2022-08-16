package com.example.notas.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notas.R
import com.example.notas.model.Note

class NoteRecyclerViewAdapter(private val notes: List<Note>) : RecyclerView.Adapter<NoteViewHolder>() {
    override fun onCreateViewHolder(parent:ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NoteViewHolder(layoutInflater.inflate(R.layout.recycler_view_row, parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = notes[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}