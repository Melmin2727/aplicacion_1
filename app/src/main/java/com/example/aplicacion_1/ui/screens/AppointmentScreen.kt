package com.example.aplicacion_1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplicacion_1.R

@Composable
fun AppointmentScreen() {
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
                painter = painterResource(id = R.drawable.perro),
                contentDescription = "Ícono de citas",
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
                text = "Citas",
                color = Color.White,
                fontSize = 16.sp
            )
        }
        // Appointment options
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text("Selecciona opciones", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SelectionBox(text = "Veterinario: Cualquiera", modifier = Modifier.weight(1f))
                SelectionBox(text = "Sucursal", modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(16.dp))
            SelectionBox(text = "Mascota: Bobby", modifier = Modifier.fillMaxWidth())
        }
        // Calendar
        CalendarComponent(Modifier.padding(horizontal = 24.dp))
        // Time Slots
        Column(modifier = Modifier.padding(24.dp)) {
            Text("Disponibilidad", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Viernes 10", color = Color.White, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TimeButton(text = "09:00", isSelected = true)
                TimeButton(text = "09:30", isSelected = false)
                TimeButton(text = "10:00", isSelected = false)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        // Confirm Button
        Button(
            onClick = { /* Lógica para confirmar */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD42E2D)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Confirmar Cita", color = Color.White)
        }
    }
}

// Composables auxiliares
@Composable
fun SelectionBox(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF262626))
            .padding(16.dp)
    ) {
        Text(text = text, color = Color.White, fontSize = 16.sp)
    }
}

@Composable
fun CalendarComponent(modifier: Modifier = Modifier) {
    // Componente de calendario
}

@Composable
fun TimeButton(text: String, isSelected: Boolean) {
    val containerColor = if (isSelected) Color(0xFFD42E2D) else Color(0xFF785B46)
    val textColor = Color.White
    Button(
        onClick = { /* Lógica */ },
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.width(100.dp)
    ) {
        Text(text, color = textColor)
    }
}