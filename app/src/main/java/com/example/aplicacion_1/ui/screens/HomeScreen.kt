package com.example.aplicacion_1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplicacion_1.R
import com.example.aplicacion_1.ui.Routes

@Composable
fun HomeScreen(
    onNavigateTo: (String) -> Unit
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
            Text(
                text = "Inicio",
                color = Color.White,
                fontSize = 16.sp
            )
        }
        // Welcome section
        Column(modifier = Modifier.padding(start = 24.dp, top = 20.dp)) {
            Text(
                text = "Hola, Cliente 👋",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Explora y cuida a tus peludos hoy",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 16.sp
            )
        }
        // Main Image Box con la imagen del perrito
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 24.dp, vertical = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF785B46)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.carrusel),
                contentDescription = "Perrito en el inicio",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.size(10.dp).clip(RoundedCornerShape(50)).background(Color.Red))
                Box(modifier = Modifier.size(10.dp).clip(RoundedCornerShape(50)).background(Color.White))
                Box(modifier = Modifier.size(10.dp).clip(RoundedCornerShape(50)).background(Color.White))
            }
        }
        // Option Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Se usa el modificador .clickable para navegar
            OptionButton(
                text = "Agendar Cita",
                imageResId = R.drawable.agendar_cita,
                onClick = { onNavigateTo(Routes.APPOINTMENT_SCREEN) }
            )
            OptionButton(
                text = "Mis Mascotas",
                imageResId = R.drawable.mis_mascotas,
                onClick = { onNavigateTo(Routes.PET_LIST_SCREEN) }
            )
            OptionButton(
                text = "Consultas Online",
                imageResId = R.drawable.consultas_veterinaria,
                onClick = { /* Agrega la ruta de consultas cuando esté lista */ }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

// Se añade un parámetro onClick a la función OptionButton
@Composable
fun OptionButton(text: String, imageResId: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF262626))
            .padding(8.dp)
            .clickable(onClick = onClick), // Usa el clic
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = text,
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text, color = Color.White, fontSize = 12.sp, textAlign = TextAlign.Center)
    }
}