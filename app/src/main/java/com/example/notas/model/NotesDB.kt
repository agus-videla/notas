package com.example.notas.model

class NotesDB {
    companion object {
        val notes = listOf(
            Note(0, "Titulo", "Lorem Ipsum"),
            Note(1, "Verduleria", "Frutas verduras"),
            Note(2, "Idea", "Hacer app de notas"),
            Note(3, "Ferreteria", "Tornillos, Tuercas"),
            Note(4, "Importante!!", "Pasear al perro"),
            Note(5, "Tareas", "Entregar TP el martes"),
            Note(6, "Super", "Yerba Cafe"),
            Note(7, "Globant", "Estudiar Recycler View"),
            Note(8, "Descargar", "black midi full album"),
            Note(9, "Cosas Importantes", "Cosas importantes que no tengo que olvidar"),
            Note(10,"Cosas para ir a la montaña", "Carpa, agua, abrigo, comida"),
            Note(11,"Organizar", "Armar horario de actividades")
        )
    }
}