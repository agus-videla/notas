package com.example.notas.utils

sealed class ApplicationStates {
    object Loading: ApplicationStates()
    data class Success(val testObject: String): ApplicationStates()
    data class Error(val errorMessage: Throwable): ApplicationStates()
}
