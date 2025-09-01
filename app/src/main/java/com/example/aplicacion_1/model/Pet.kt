package com.example.aplicacion_1.model

data class Pet(
    val id: Int,
    val name: String,
    val age: String,
    val breed: String,
    val photo: Int // Int se usa para el ID del recurso de imagen
)