// Archivo: PetRepository.kt
package com.example.aplicacion_1.data

import com.example.aplicacion_1.model.Pet
import kotlinx.coroutines.flow.Flow

class PetRepository(private val petDao: PetDao) {
    val allPets: Flow<List<Pet>> = petDao.getAllPets()

    suspend fun insert(pet: Pet) {
        petDao.insertPet(pet)
    }

    suspend fun update(pet: Pet) {
        petDao.updatePet(pet)
    }

    suspend fun delete(pet: Pet) {
        petDao.deletePet(pet)
    }
}