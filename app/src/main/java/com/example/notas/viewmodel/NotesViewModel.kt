package com.example.notas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notas.model.Note
import com.example.notas.model.NotesDB

class NotesViewModel : ViewModel() {
    private val _allNotes = MutableLiveData<MutableList<Note>>(NotesDB.notes.toMutableList())
    private val _currentNote = MutableLiveData<Note>(NotesDB.notes[0])
    val allNotes: LiveData<MutableList<Note>> get() = _allNotes
    val currentNote: LiveData<Note> get() = _currentNote

    fun setCurrentNote(index: Int) {
        _currentNote.value = _allNotes.value?.get(index)
    }

    fun setCurrentEmptyNote() {
        val highestStoredId = _allNotes.value?.lastIndex ?: -1
        _currentNote.value = Note(highestStoredId+1,"","")
    }

    fun removeNoteAt(index: Int) {
        _allNotes.value?.removeAt(index)
    }

    fun addNote(note: Note) {
        _allNotes.value?.add(note)
    }

    fun isStored(note: Note?): Boolean {
        val highestStoredId = _allNotes.value?.lastIndex ?: -1
        return note!!.id <= highestStoredId
    }
}