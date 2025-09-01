package com.example.aplicacion_1.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.aplicacion_1.data.addPet
import com.example.aplicacion_1.model.Pet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPetScreen(onPetAdded: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var breed by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

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
            text = "Añadir Nueva Mascota",
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
                val newId = (100..1000).random()
                val newPet = Pet(
                    id = newId,
                    name = name,
                    age = age,
                    breed = breed,
                    photoUri = selectedImageUri
                )
                addPet(newPet)
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
        colors = TextFieldDefaults.colors( // <-- ¡Línea corregida!
            focusedContainerColor = Color(0xFF262626), // Añade color para el estado de foco
            unfocusedContainerColor = Color(0xFF262626), // Añade color para el estado sin foco
            disabledContainerColor = Color(0xFF262626),
            cursorColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        singleLine = true
    )
}