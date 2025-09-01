// Archivo: UserPreferences.kt
package com.example.aplicacion_1.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

// Crea una extensión para DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    // Define las claves para los datos del usuario
    companion object {
        val USER_NAME_KEY = stringPreferencesKey("user_name")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }

    // Guarda los datos del usuario
    suspend fun saveUserDetails(name: String, email: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_NAME_KEY] = name
            prefs[USER_EMAIL_KEY] = email
        }
    }

    // Lee el nombre del usuario
    suspend fun getUserName(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[USER_NAME_KEY]
    }

    // Lee el correo del usuario
    suspend fun getUserEmail(): String? {
        val prefs = context.dataStore.data.first()
        return prefs[USER_EMAIL_KEY]
    }
}