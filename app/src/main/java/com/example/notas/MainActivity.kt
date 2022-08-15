package com.example.notas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notas.databinding.ActivityMainBinding
import com.example.notas.model.NotesDB

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    fun initRecyclerView() {
        val recyclerview = findViewById<RecyclerView>(R.id.rvNotes)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = NoteRecyclerViewAdapter(NotesDB.notes)

    }
}