package com.example.aplicacion_1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
import com.example.aplicacion_1.R
import com.example.aplicacion_1.model.Pet

@Composable
fun PetProfileScreen(
    pet: Pet
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4B3322))
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 24.dp, end = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.usuario),
                contentDescription = "Ícono de usuario",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Text(
                text = "PawFriends",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painter = painterResource(id = R.drawable.agendar_cita),
                contentDescription = "Ícono de edición",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )
        }
        // Pet Info
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = pet.photo), // Usa la foto de la mascota
                    contentDescription = "Foto de perfil de ${pet.name}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.dp, Color.White, RoundedCornerShape(16.dp))
                )
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(text = pet.name, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold) // Usa el nombre de la mascota
                    Text(text = "${pet.age} • ${pet.breed}", color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp) // Usa edad y raza
                }
            }
        }
        // Info Cards
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InfoCard(title = "Datos") {
                InfoRow(label = "Nombre", value = pet.name)
                InfoRow(label = "Edad", value = pet.age)
                InfoRow(label = "Raza", value = pet.breed)
                InfoRow(label = "Peso", value = "24 kg") // Puedes mantener un valor estático por ahora
            }
            InfoCard(title = "Vacunas") {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    VaccineButton(text = "Rabia — 2025")
                    VaccineButton(text = "Quíntuple — 2025")
                    VaccineButton(text = "Bordetella — 2024")
                }
                Button(
                    onClick = { /* Lógica */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD42E2D)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Añadir vacuna", color = Color.White)
                }
            }
            InfoCard(title = "Historial") {
                HistoryItem(title = "Consulta general", date = "12 Ago 2025", location = "Dr. Pérez • Sucursal Centro")
                HistoryItem(title = "Vacuna Rabia", date = "20 Feb 2025", location = "Aplicada")
                HistoryItem(title = "Desparasitación", date = "05 Dic 2024", location = "Producto X")
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun HistoryItem(title: String, date: String, location: String) {
    TODO("Not yet implemented")
}

@Composable
fun VaccineButton(text: String) {
    TODO("Not yet implemented")
}

@Composable
fun InfoRow(label: String, value: String) {
    TODO("Not yet implemented")
}

@Composable
fun InfoCard(title: String, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF262626))
            .padding(16.dp)
    ) {
        Column {
            Text(text = title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            content() // Llama a la función que contiene los composables
        }
    }
}