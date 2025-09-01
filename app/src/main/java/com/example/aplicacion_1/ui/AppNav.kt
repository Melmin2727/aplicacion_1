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
import com.example.aplicacion_1.model.Pet
import com.example.aplicacion_1.ui.screens.AddPetScreen
import com.example.aplicacion_1.ui.screens.AppointmentScreen
import com.example.aplicacion_1.ui.screens.EditPetScreen
import com.example.aplicacion_1.ui.screens.HomeScreen
import com.example.aplicacion_1.ui.screens.PetListScreen
import com.example.aplicacion_1.ui.screens.PetProfileScreen
import com.example.aplicacion_1.ui.screens.SplashScreen
import com.example.aplicacion_1.ui.screens.LoginScreen // Importación de la nueva pantalla
import androidx.compose.runtime.collectAsState
// Define las rutas de la aplicación
object Routes {
    const val BOTTOM_NAV = "bottom_nav"
    const val SPLASH_SCREEN = "splash_screen" // Nueva ruta
    const val LOGIN_SCREEN = "login_screen" // Nueva ruta
    const val HOME_SCREEN = "home_screen"
    const val APPOINTMENT_SCREEN = "appointment_screen"
    const val PET_LIST_SCREEN = "pet_list_screen"
    const val ADD_PET_SCREEN = "add_pet_screen"
    const val PET_PROFILE_SCREEN = "pet_profile_screen/{petId}"
    const val EDIT_PET_SCREEN = "edit_pet_screen/{petId}"
    const val PROFILE_SCREEN = "profile_screen"
}

@Composable
fun AppNav() {
    val navController = rememberNavController()

    // El BottomNav se mostrará solo en las pantallas principales
    val showBottomBar = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)
        .value?.destination?.route in listOf(
        Routes.HOME_SCREEN,
        Routes.APPOINTMENT_SCREEN,
        Routes.PET_LIST_SCREEN,
        Routes.PROFILE_SCREEN
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNav(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.SPLASH_SCREEN, // La app ahora inicia con el SplashScreen
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.SPLASH_SCREEN) {
                SplashScreen(
                    onTimeout = {
                        navController.navigate(Routes.LOGIN_SCREEN) {
                            popUpTo(Routes.SPLASH_SCREEN) { inclusive = true }
                        }
                    }
                )
            }
            composable(Routes.LOGIN_SCREEN) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Routes.HOME_SCREEN) {
                            popUpTo(Routes.LOGIN_SCREEN) { inclusive = true }
                        }
                    }
                )
            }

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
                    PetProfileScreen(
                        pet = selectedPet,
                        onEditClicked = {
                            navController.navigate("edit_pet_screen/${selectedPet.id}")
                        }
                    )
                }
            }
            composable(
                route = Routes.EDIT_PET_SCREEN,
                arguments = listOf(navArgument("petId") { type = NavType.IntType })
            ) { backStackEntry ->
                val petId = backStackEntry.arguments?.getInt("petId")
                val pet = pets.find { it.id == petId }
                if (pet != null) {
                    EditPetScreen(
                        pet = pet,
                        onPetUpdated = { navController.popBackStack() },
                        onPetDeleted = {
                            navController.popBackStack()
                            navController.popBackStack()
                        }
                    )
                } else {
                    navController.popBackStack()
                }
            }
            composable(Routes.PROFILE_SCREEN) {
                // Aquí irá la pantalla de perfil de usuario con los datos de registro
            }
        }
    }
}