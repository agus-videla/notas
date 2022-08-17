package com.example.notas.model

class NotesDB {
    companion object {
        val notes = listOf(
            Note("Titulo", "Lorem Ipsum"),
            Note("Verduleria", "Frutas verduras"),
            Note("Idea", "Hacer app de notas"),
            Note("Ferreteria", "Tornillos, Tuercas"),
            Note("Importante!!", "Pasear al perro"),
            Note("Tareas", "Entregar TP el martes"),
            Note("Super", "Yerba Cafe"),
            Note("Globant", "Estudiar Recycler View"),
            Note("Descargar", "black midi full album"),
            Note("Cosas Importantes", "Cosas importantes que no tengo que olvidar"),
            Note("Cosas para ir a la montaña", "Carpa, agua, abrigo, comida"),
            Note("Organizar", "Armar horario de actividades")
        )
    }
}