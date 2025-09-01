package com.example.aplicacion_1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplicacion_1.data.pets
import com.example.aplicacion_1.model.Pet

@Composable
fun PetListScreen(
    onAddPetClicked: () -> Unit,
    onPetClicked: (Pet) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF4B3322))
                .padding(top = 50.dp, start = 24.dp, end = 24.dp)
        ) {
            Text(
                text = "Mis Mascotas",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Lista de mascotas
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(pets) { pet ->
                    PetCard(pet = pet, onPetClicked = onPetClicked)
                }
            }
        }
        // Botón flotante para agregar mascota
        FloatingActionButton(
            onClick = onAddPetClicked,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            containerColor = Color(0xFFD42E2D),
            shape = CircleShape
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Añadir mascota", tint = Color.White)
        }
    }
}

@Composable
fun PetCard(pet: Pet, onPetClicked: (Pet) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF262626))
            .clickable { onPetClicked(pet) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = pet.photo),
            contentDescription = "Foto de ${pet.name}",
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = pet.name, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "${pet.age} • ${pet.breed}", color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp)
        }
    }
}