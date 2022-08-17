package com.example.notas.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notas.databinding.ActivityMainBinding
import com.example.notas.model.Note
import com.example.notas.model.NotesDB
import com.example.notas.recyclerview.NoteRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mutableNoteList: MutableList<Note> = NotesDB.notes.toMutableList()
    private lateinit var adapter: NoteRecyclerViewAdapter
    private val llmanager: LinearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAdd.setOnClickListener {
            onItemAdded((1..mutableNoteList.size).random())
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = NoteRecyclerViewAdapter(
            mutableNoteList,
            { note -> onItemSelected(note) },
            { position -> onItemDeleted(position) }
        )
        binding.rvNotes.layoutManager = llmanager
        binding.rvNotes.adapter = adapter
    }

    private fun onItemSelected(note: Note) {
        Toast.makeText(this, note.title, Toast.LENGTH_SHORT).show()
    }

    private fun onItemDeleted(position: Int) {
        mutableNoteList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    private fun onItemAdded(position: Int) {
        mutableNoteList.add(position, Note("Nueva Nota", "Soy muy importante"))
        adapter.notifyItemInserted(position)
        llmanager.scrollToPosition(position)
    }

}