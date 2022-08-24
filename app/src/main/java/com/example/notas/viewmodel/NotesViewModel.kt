package com.example.notas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notas.model.Note
import com.example.notas.model.NotesDB

class NotesViewModel : ViewModel() {
    private val _allNotes = MutableLiveData<MutableList<Note>>()
    private val _currentNote = MutableLiveData<Note>()
    val allNotes: LiveData<MutableList<Note>> get() = _allNotes
    val currentNote: LiveData<Note> get() = _currentNote

    fun startModel() {
        _allNotes.value = NotesDB.notes
        _currentNote.value = NotesDB.notes[0]
    }

    fun setCurrentNote(index: Int) {
        _currentNote.value = NotesDB.notes[index]
    }
}