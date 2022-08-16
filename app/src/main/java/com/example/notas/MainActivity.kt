package com.example.notas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notas.databinding.ActivityMainBinding
import com.example.notas.model.NotesDB
import com.example.notas.recyclerview.NoteRecyclerViewAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.adapter = NoteRecyclerViewAdapter(NotesDB.notes)

    }
}