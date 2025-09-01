package com.example.aplicacion_1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplicacion_1.R
import com.example.aplicacion_1.data.UserPreferences
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val userPreferences = UserPreferences(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4B3322))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título de la pantalla
        Text(
            text = "Bienvenido",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = "Accede o crea tu cuenta",
            fontSize = 16.sp,
            color = Color.White.copy(alpha = 0.7f),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Campos de texto para Correo y Contraseña
        OutlinedTextField(
            value = email,
            onValueChange = setEmail,
            label = { Text("Correo electrónico", color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF262626),
                unfocusedContainerColor = Color(0xFF262626),
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = setPassword,
            label = { Text("Contraseña", color = Color.Gray) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF262626),
                unfocusedContainerColor = Color(0xFF262626),
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Botón de Continuar
        Button(
            onClick = {
                // Guarda los datos del usuario y luego navega
                coroutineScope.launch {
                    userPreferences.saveUserDetails("Usuario", email)
                    onLoginSuccess()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD42E2D)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Continuar", color = Color.White, modifier = Modifier.padding(vertical = 4.dp))
        }

        // ¿Olvidaste tu contraseña?
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿Olvidaste tu contraseña?",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Recuperar",
                color = Color(0xFFD42E2D),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    // Lógica para ir a la pantalla de recuperación
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        // Botón de Google (separador y botón)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Divider(
                color = Color.White.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxWidth().height(1.dp)
            )
            Text(
                text = "o",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp,
                modifier = Modifier
                    .background(Color(0xFF4B3322))
                    .padding(horizontal = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                // Lógica de inicio de sesión con Google
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Asegúrate de tener un recurso de logo de Google
                Image(
                    painter = painterResource(id = R.drawable.perro),
                    contentDescription = "Google Logo",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    "Continuar con Google",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ¿No tienes cuenta? Regístrate
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿No tienes cuenta?",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Regístrate",
                color = Color(0xFFD42E2D),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    // Lógica para ir a la pantalla de registro
                }
            )
        }
    }
}