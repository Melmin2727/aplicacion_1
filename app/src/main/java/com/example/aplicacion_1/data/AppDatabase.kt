package com.example.aplicacion_1.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.aplicacion_1.model.Pet
import android.net.Uri

@Database(entities = [Pet::class], version = 1, exportSchema = false)
@TypeConverters(AppDatabase.Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao

    class Converters {
        @TypeConverter
        fun fromUri(uri: Uri?): String? {
            return uri?.toString()
        }

        @TypeConverter
        fun toUri(uriString: String?): Uri? {
            return if (uriString == null) null else Uri.parse(uriString)
        }
    }
}
