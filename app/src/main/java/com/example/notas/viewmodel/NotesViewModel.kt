package com.example.notas.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notas.model.Note
import com.example.notas.utils.ApplicationStates
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileNotFoundException

class NotesViewModel(context: Context?) : ViewModel() {
    private val FILEPATH = context?.filesDir.toString()
    private val FILENAME = "notes.json"
    private val _loadingState = MutableLiveData<ApplicationStates>(ApplicationStates.Loading)
    private val _allNotes = MutableLiveData<MutableList<Note>>()
    private val _currentNote = MutableLiveData<Note>()
    val allNotes: LiveData<MutableList<Note>> get() = _allNotes
    val currentNote: LiveData<Note> get() = _currentNote
    val loadingState: LiveData<ApplicationStates> get() = _loadingState

    fun setNotes() {
        val notes = try {
            read()
        } catch (e: FileNotFoundException) {
            emptyList<Note>()
        }
        _allNotes.postValue(notes.toMutableList())
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

    fun isStored(note: Note): Boolean {
        val highestStoredId = _allNotes.value?.lastIndex ?: -1
        return note.id <= highestStoredId
    }

    private fun write(notes: List<Note>) {
        val json = Gson().toJson(notes)
        File(FILEPATH + FILENAME).writeText(json)
    }

    private fun read(): List<Note> {
        val json = File(FILEPATH + FILENAME).readText()
        val jType = object : TypeToken<List<Note>>() {}.type
        return Gson().fromJson(json, jType)
    }

    fun save() {
        write(_allNotes.value?.toList() ?: listOf())
    }
}

@Suppress("UNCHECKED_CAST")
class NotesViewModelFactory(private val context: Context?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(context) as T
    }
}

