package com.example.aplicacion_1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.net.Uri

@Entity(tableName = "pets")
data class Pet(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val age: String,
    val breed: String,
    val photoUri: Uri?
)