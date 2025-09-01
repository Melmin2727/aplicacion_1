package com.example.aplicacion_1.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aplicacion_1.data.pets
import com.example.aplicacion_1.ui.screens.AddPetScreen
import com.example.aplicacion_1.ui.screens.AppointmentScreen
import com.example.aplicacion_1.ui.screens.HomeScreen
import com.example.aplicacion_1.ui.screens.PetListScreen
import com.example.aplicacion_1.ui.screens.PetProfileScreen

// Define las rutas de la aplicación
object Routes {
    const val HOME_SCREEN = "home_screen"
    const val APPOINTMENT_SCREEN = "appointment_screen"
    const val PET_LIST_SCREEN = "pet_list_screen"
    const val ADD_PET_SCREEN = "add_pet_screen"
    const val PET_PROFILE_SCREEN = "pet_profile_screen/{petId}" // Nueva ruta con argumento
    const val PROFILE_SCREEN = "profile_screen" // Nueva ruta para el perfil de usuario
}

@Composable
fun AppNav() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNav(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.HOME_SCREEN,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME_SCREEN) {
                HomeScreen(
                    onNavigateTo = { route -> navController.navigate(route) }
                )
            }
            composable(Routes.APPOINTMENT_SCREEN) {
                AppointmentScreen()
            }
            composable(Routes.PET_LIST_SCREEN) {
                PetListScreen(
                    onAddPetClicked = { navController.navigate(Routes.ADD_PET_SCREEN) },
                    onPetClicked = { pet ->
                        navController.navigate("pet_profile_screen/${pet.id}")
                    }
                )
            }
            composable(Routes.ADD_PET_SCREEN) {
                AddPetScreen(
                    onPetAdded = { navController.popBackStack() }
                )
            }
            composable(
                route = Routes.PET_PROFILE_SCREEN,
                arguments = listOf(navArgument("petId") { type = NavType.IntType })
            ) { backStackEntry ->
                val petId = backStackEntry.arguments?.getInt("petId")
                val selectedPet = pets.find { it.id == petId }

                if (selectedPet != null) {
                    PetProfileScreen(pet = selectedPet)
                }
            }
            composable(Routes.PROFILE_SCREEN) {
                // Aquí iría tu pantalla de perfil de usuario
            }
        }
    }
}