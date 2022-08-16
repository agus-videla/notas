package com.example.notas.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.notas.databinding.ActivityNoteBinding
import com.example.notas.viewmodel.NotesViewModel

class NoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteBinding
    private val noteViewModel : NotesViewModel = NotesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteViewModel.noteModel.observe(this, Observer {
            binding.tvNoteTitle.text = it!!.title
            binding.tvNoteBody.text = it.body
        })

        binding.constraintLayout.setOnClickListener {
            noteViewModel.getNote()
        }
    }
}