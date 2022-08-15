package com.example.notas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notas.model.Note
import com.example.notas.model.NotesDB

class NotesViewModel : ViewModel() {

    val noteModel = MutableLiveData<Note?>()

    fun getNote() {
        val currentNote = NotesDB.select(1)
        noteModel.postValue(currentNote)
    }
}