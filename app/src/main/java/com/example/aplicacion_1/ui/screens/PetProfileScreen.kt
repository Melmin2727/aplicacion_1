package com.example.aplicacion_1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pets // <-- Importación correcta
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
import coil.compose.rememberAsyncImagePainter
import com.example.aplicacion_1.R
import com.example.aplicacion_1.model.Pet

@Composable
fun PetProfileScreen(
    pet: Pet,
    onEditClicked: (Pet) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4B3322))
    ) {
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
            // Aquí falta el botón de editar
            Image(
                painter = painterResource(id = R.drawable.perro), // Asegúrate de tener este recurso
                contentDescription = "Ícono de edición",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .clickable { onEditClicked(pet) } // Agrega el click
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (pet.photoUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(pet.photoUri),
                        contentDescription = "Foto de perfil de ${pet.name}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .border(1.dp, Color.White, RoundedCornerShape(16.dp))
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.Gray)
                            .border(1.dp, Color.White, RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Pets, contentDescription = "Sin foto", tint = Color.White, modifier = Modifier.size(60.dp))
                    }
                }
                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(text = pet.name, color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(text = "${pet.age} • ${pet.breed}", color = Color.White.copy(alpha = 0.7f), fontSize = 14.sp)
                }
            }
        }
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InfoCard(title = "Datos") {
                InfoRow(label = "Nombre", value = pet.name)
                InfoRow(label = "Edad", value = pet.age)
                InfoRow(label = "Raza", value = pet.breed)
                InfoRow(label = "Peso", value = "24 kg")
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
            content()
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.White, fontSize = 14.sp)
        Text(text = value, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun VaccineButton(text: String) {
    Button(
        onClick = { /* Lógica */ },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF785B46)),
        shape = RoundedCornerShape(12.dp),
    ) {
        Text(text, color = Color.White, fontSize = 12.sp)
    }
}

@Composable
fun HistoryItem(title: String, date: String, location: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(Color.Red)
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(text = title, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "$date • $location", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
        }
    }
}