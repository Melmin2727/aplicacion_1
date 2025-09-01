// Archivo: PetDao.kt
package com.example.aplicacion_1.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.aplicacion_1.model.Pet
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Insert
    suspend fun insertPet(pet: Pet)

    @Update
    suspend fun updatePet(pet: Pet)

    @Delete
    suspend fun deletePet(pet: Pet)

    @Query("SELECT * FROM pets ORDER BY name ASC")
    fun getAllPets(): Flow<List<Pet>>
}