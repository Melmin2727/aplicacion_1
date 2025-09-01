package com.example.aplicacion_1.model

import android.net.Uri

data class Pet(
    val id: Int,
    val name: String,
    val age: String,
    val breed: String,
    val photoUri: Uri? = null // Usa Uri para la imagen
)