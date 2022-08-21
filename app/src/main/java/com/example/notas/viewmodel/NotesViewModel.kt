package com.example.notas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notas.model.Note
import com.example.notas.model.NotesDB

class NotesViewModel : ViewModel() {

    val noteModel = MutableLiveData<Note>()
    var currentIndex: Int = 0

    fun getNote() {
        val currentNote = NotesDB.notes[currentIndex]
        noteModel.postValue(currentNote)
    }

    fun setCurrent(current: Int) {
        currentIndex = current
    }
}