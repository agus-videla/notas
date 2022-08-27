package com.example.notas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notas.model.Note
import com.example.notas.model.NotesDB
import com.example.notas.utils.ApplicationStates

class NotesViewModel : ViewModel() {
    private val _loadingState = MutableLiveData<ApplicationStates>(ApplicationStates.Loading)
    private val _allNotes = MutableLiveData<MutableList<Note>>()
    private val _currentNote = MutableLiveData<Note>(NotesDB.notes[0])
    val allNotes: LiveData<MutableList<Note>> get() = _allNotes
    val currentNote: LiveData<Note> get() = _currentNote
    val loadingState: LiveData<ApplicationStates> get() = _loadingState

    fun setNotes() {
        Thread.sleep(5000)
        _allNotes.postValue(NotesDB.notes.toMutableList())
        _loadingState.postValue(ApplicationStates.Success("Loaded Notes"))
    }

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