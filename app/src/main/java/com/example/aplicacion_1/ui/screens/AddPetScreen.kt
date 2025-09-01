package com.example.aplicacion_1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplicacion_1.data.addPet
import com.example.aplicacion_1.model.Pet
import com.example.aplicacion_1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPetScreen(onPetAdded: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var breed by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4B3322))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Añadir Nueva Mascota",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Campo de texto para el nombre
        CustomTextField(
            value = name,
            onValueChange = { name = it },
            label = "Nombre de la mascota"
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para la edad
        CustomTextField(
            value = age,
            onValueChange = { age = it },
            label = "Edad (ej: 3 años)"
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para la raza
        CustomTextField(
            value = breed,
            onValueChange = { breed = it },
            label = "Raza"
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Botón para guardar
        Button(
            onClick = {
                // Genera un ID único (aquí se usa un número aleatorio para simplificar)
                val newId = (100..1000).random()
                val newPet = Pet(
                    id = newId,
                    name = name,
                    age = age,
                    breed = breed,
                    photo = R.drawable.perro // Imagen por defecto
                )
                // Llama a la función del CRUD para añadir la mascota
                addPet(newPet)
                // Regresa a la pantalla anterior
                onPetAdded()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD42E2D)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Guardar Mascota", color = Color.White)
        }
    }
}

// Composable para campos de texto reutilizables
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, label: String) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = Color.White) },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF262626),
            unfocusedContainerColor = Color(0xFF262626),
            disabledContainerColor = Color(0xFF262626),
            cursorColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}
