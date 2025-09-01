package com.example.aplicacion_1.data

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.aplicacion_1.R // Asegúrate de que esta importación exista
import com.example.aplicacion_1.model.Pet

// Esta función auxiliar convierte un ID de recurso de drawable en una Uri.
// Usamos el nombre del paquete de la aplicación para crear la ruta.
fun getUriFromDrawable(resourceId: Int): Uri {
    return Uri.parse("android.resource://com.example.aplicacion_1/$resourceId")
}

val pets: SnapshotStateList<Pet> = mutableStateListOf(
    Pet(1, "Bobby", "3 años", "Golden Retriever", photoUri = getUriFromDrawable(R.drawable.perro)),
    Pet(2, "Rocky", "2 años", "Pastor Alemán", photoUri = getUriFromDrawable(R.drawable.perro)),
    Pet(3, "Max", "5 años", "Labrador", photoUri = getUriFromDrawable(R.drawable.perro))
)

fun addPet(newPet: Pet) {
    pets.add(newPet)
}