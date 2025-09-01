package com.example.aplicacion_1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplicacion_1.R

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4B3322))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo y título
        Image(
            painter = painterResource(id = R.drawable.perro),
            contentDescription = "PawFriends Logo",
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = "Bienvenido a PawFriends",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        )

        // Campos de texto para Correo y Contraseña
        OutlinedTextField(
            value = email,
            onValueChange = setEmail,
            label = { Text("Correo electrónico") },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
            colors = TextFieldDefaults.colors( // <-- ¡Código corregido!
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = setPassword,
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
            colors = TextFieldDefaults.colors( // <-- ¡Código corregido!
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Botón de Continuar
        Button(
            onClick = {
                // Aquí iría tu lógica de autenticación
                // Por ahora, solo navegaremos a la siguiente pantalla
                onLoginSuccess()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD42E2D)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Continuar", color = Color.White)
        }
    }
}