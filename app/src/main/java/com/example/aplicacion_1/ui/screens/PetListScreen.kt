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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.aplicacion_1.data.pets
import com.example.aplicacion_1.model.Pet

@Composable
fun PetListScreen(
    onAddPetClicked: () -> Unit,
    onPetClicked: (Pet) -> Unit,
    onEditClicked: (Pet) -> Unit, // <-- Agregado para la acción de editar
    onDeleteClicked: (Pet) -> Unit // <-- Agregado para la acción de eliminar
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

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(pets) { pet ->
                    PetCard(
                        pet = pet,
                        onPetClicked = onPetClicked,
                        onEditClicked = onEditClicked,
                        onDeleteClicked = onDeleteClicked
                    )
                }
            }
        }
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
fun PetCard(
    pet: Pet,
    onPetClicked: (Pet) -> Unit,
    onEditClicked: (Pet) -> Unit,
    onDeleteClicked: (Pet) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF262626))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // La información de la mascota
        Row(
            modifier = Modifier
                .weight(1f)
                .clickable { onPetClicked(pet) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (pet.photoUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(pet.photoUri),
                    contentDescription = "Foto de ${pet.name}",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Pets, contentDescription = "Sin foto", tint = Color.White)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = pet.name, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "${pet.age} • ${pet.breed}", color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp)
            }
        }

        // Los botones de editar y eliminar
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onEditClicked(pet) }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar mascota",
                    tint = Color.White
                )
            }
            IconButton(onClick = { onDeleteClicked(pet) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar mascota",
                    tint = Color.Red
                )
            }
        }
    }
}