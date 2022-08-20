package com.example.notas.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.notas.R
import com.example.notas.databinding.ActivityMainBinding
import com.example.notas.model.Note
import com.example.notas.model.NotesDB

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var noteList: List<Note> = NotesDB.notes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(SummaryFragment(noteList.toMutableList()))
    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()
    }
}