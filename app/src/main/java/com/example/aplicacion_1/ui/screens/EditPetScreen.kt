package com.example.aplicacion_1.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.aplicacion_1.data.deletePet
import com.example.aplicacion_1.data.updatePet
import com.example.aplicacion_1.model.Pet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPetScreen(pet: Pet, onPetUpdated: () -> Unit, onPetDeleted: () -> Unit) {
    var name by remember { mutableStateOf(pet.name) }
    var age by remember { mutableStateOf(pet.age) }
    var breed by remember { mutableStateOf(pet.breed) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(pet.photoUri) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            selectedImageUri = uri
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4B3322))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Editar a ${pet.name}",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF785B46))
                .clickable { imagePickerLauncher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            if (selectedImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(selectedImageUri),
                    contentDescription = "Imagen de la mascota",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Default.AddAPhoto,
                    contentDescription = "Añadir foto de mascota",
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
        Text(
            text = "Toca para añadir foto",
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )

        CustomTextField(
            value = name,
            onValueChange = { name = it },
            label = "Nombre de la mascota"
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = age,
            onValueChange = { age = it },
            label = "Edad (ej: 3 años)"
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextField(
            value = breed,
            onValueChange = { breed = it },
            label = "Raza"
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val updatedPet = pet.copy(
                    name = name,
                    age = age,
                    breed = breed,
                    photoUri = selectedImageUri
                )
                updatePet(updatedPet)
                onPetUpdated()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD42E2D)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Guardar Cambios", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = {
                deletePet(pet.id)
                onPetDeleted()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFD42E2D)),
            border = BorderStroke(2.dp, Color(0xFFD42E2D)), // <-- Línea corregida
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Eliminar Mascota")
        }
    }
}