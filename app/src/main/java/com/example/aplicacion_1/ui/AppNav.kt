package com.example.aplicacion_1.ui

import androidx.compose.runtime.Composable
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
}

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Routes.HOME_SCREEN) {
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
    }
}