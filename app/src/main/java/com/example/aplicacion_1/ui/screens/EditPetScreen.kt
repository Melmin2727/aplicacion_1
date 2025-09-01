// Archivo: EditPetScreen.kt
package com.example.aplicacion_1.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.aplicacion_1.model.Pet

@Composable
fun EditPetScreen(
    pet: Pet,
    onPetUpdated: (Pet) -> Unit, // <-- Pasa la mascota actualizada como parámetro
    onPetDeleted: () -> Unit
) {
    var name by remember { mutableStateOf(pet.name) }
    var age by remember { mutableStateOf(pet.age) }
    var breed by remember { mutableStateOf(pet.breed) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Editar Mascota", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre de la mascota") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = breed,
            onValueChange = { breed = it },
            label = { Text("Raza") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Botones para guardar y eliminar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {
                    val updatedPet = pet.copy(name = name, age = age, breed = breed)
                    onPetUpdated(updatedPet) // <-- Llama al callback y le pasa la mascota actualizada
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Guardar")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    onPetDeleted() // <-- Llama al callback para eliminar la mascota
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Eliminar")
            }
        }
    }
}