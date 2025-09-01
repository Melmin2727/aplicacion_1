// Archivo: PetDatabase.kt
package com.example.aplicacion_1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aplicacion_1.model.Pet

@Database(entities = [Pet::class], version = 1, exportSchema = false)
abstract class PetDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao

    companion object {
        @Volatile
        private var Instance: PetDatabase? = null

        fun getDatabase(context: Context): PetDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PetDatabase::class.java, "pet_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}