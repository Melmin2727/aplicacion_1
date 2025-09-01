package com.example.aplicacion_1.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.aplicacion_1.R
import com.example.aplicacion_1.model.Pet

// Lista mutable de mascotas para simular una base de datos en memoria
val pets: SnapshotStateList<Pet> = mutableStateListOf(
    Pet(1, "Bobby", "3 años", "Golden Retriever", R.drawable.perro),
    Pet(2, "Rocky", "2 años", "Pastor Alemán", R.drawable.perro),
    Pet(3, "Max", "5 años", "Labrador", R.drawable.perro)
)

// Función para añadir una nueva mascota a la lista
fun addPet(newPet: Pet) {
    pets.add(newPet)
}