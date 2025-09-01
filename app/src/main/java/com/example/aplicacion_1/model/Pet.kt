package com.example.aplicacion_1.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pet(
    val id: Int,
    var name: String,
    var age: String,
    var breed: String,
    val photoId: Int? = null,
    var photoUri: Uri? = null
) : Parcelable