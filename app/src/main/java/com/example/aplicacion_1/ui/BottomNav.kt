package com.example.aplicacion_1.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

import com.example.aplicacion_1.ui.Routes

@Composable
fun BottomNav(navController: NavController) {
    NavigationBar(
        containerColor = Color(0xFF262626),
        modifier = Modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        // Botón de Inicio
        NavigationBarItem(
            selected = currentRoute == Routes.HOME_SCREEN,
            onClick = { navController.navigate(Routes.HOME_SCREEN) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home, // Ícono de casa
                    contentDescription = "Inicio"
                )
            },
            label = { Text("Inicio") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFFD42E2D),
                unselectedIconColor = Color.White,
                selectedTextColor = Color(0xFFD42E2D),
                unselectedTextColor = Color.White
            )
        )

        // Botón de Citas
        NavigationBarItem(
            selected = currentRoute == Routes.APPOINTMENT_SCREEN,
            onClick = { navController.navigate(Routes.APPOINTMENT_SCREEN) },
            icon = {
                Icon(
                    imageVector = Icons.Default.DateRange, // Ícono de calendario
                    contentDescription = "Citas"
                )
            },
            label = { Text("Citas") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFFD42E2D),
                unselectedIconColor = Color.White,
                selectedTextColor = Color(0xFFD42E2D),
                unselectedTextColor = Color.White
            )
        )

        // Botón de Mascotas
        NavigationBarItem(
            selected = currentRoute == Routes.PET_LIST_SCREEN,
            onClick = { navController.navigate(Routes.PET_LIST_SCREEN) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Pets, // Ícono de pata/animal
                    contentDescription = "Mascotas"
                )
            },
            label = { Text("Mascotas") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFFD42E2D),
                unselectedIconColor = Color.White,
                selectedTextColor = Color(0xFFD42E2D),
                unselectedTextColor = Color.White
            )
        )

        // Botón de Perfil
        NavigationBarItem(
            selected = currentRoute == Routes.PROFILE_SCREEN,
            onClick = { navController.navigate(Routes.PROFILE_SCREEN) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person, // Ícono de persona
                    contentDescription = "Perfil"
                )
            },
            label = { Text("Perfil") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFFD42E2D),
                unselectedIconColor = Color.White,
                selectedTextColor = Color(0xFFD42E2D),
                unselectedTextColor = Color.White
            )
        )
    }
}