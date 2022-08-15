package com.example.notas.model

class NotesDB {
    companion object {
        private val notes = listOf(
            Note("Titulo", "Lorem Ipsum"),
            Note("Verduleria", "Frutas verduras"),
            Note("Idea", "Hacer app de notas"),
            Note("Ferreteria", "Tornillos, Tuercas"),
            Note("Importante!!", "Pasear al perro"),
            Note("Tareas", "Entregar TP el martes"),
            Note("Super", "Yerba Cafe"),
            Note("Globant", "Estudiar Recycler View"),
            Note("Descargar", "black midi full album"),
            Note("Organizar", "Armar horario de actividades")
        )

        fun select(id: Int) : Note {
            return notes[id]
        }

        fun getAll() : List<Note> {
            return notes
        }

        fun getSize(): Int {
            return notes.size
        }
    }
}