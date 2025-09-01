// Archivo: PetApplication.kt
package com.example.aplicacion_1

import android.app.Application
import com.example.aplicacion_1.data.PetDatabase
import com.example.aplicacion_1.data.PetRepository

class PetApplication : Application() {
    // Se usa lazy para que la base de datos y el repositorio se creen solo cuando se necesiten
    val database by lazy { PetDatabase.getDatabase(this) }
    val repository by lazy { PetRepository(database.petDao()) }
}