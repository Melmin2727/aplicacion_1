package com.example.aplicacion_1.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.aplicacion_1.PetApplication
import com.example.aplicacion_1.ui.screens.*
import com.example.aplicacion_1.ui.viewmodel.PetViewModel
import com.example.aplicacion_1.ui.viewmodel.PetViewModelFactory

// Define las rutas de la aplicación
object Routes {
    const val BOTTOM_NAV = "bottom_nav"
    const val SPLASH_SCREEN = "splash_screen"
    const val LOGIN_SCREEN = "login_screen"
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
    val context = LocalContext.current
    // Instancia el ViewModel para que esté disponible en toda la navegación
    val petViewModel: PetViewModel = viewModel(
        factory = PetViewModelFactory((context.applicationContext as PetApplication).repository)
    )

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
            startDestination = Routes.SPLASH_SCREEN,
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
                // Pasa el viewModel a la pantalla
                PetListScreen(
                    viewModel = petViewModel,
                    onAddPetClicked = { navController.navigate(Routes.ADD_PET_SCREEN) },
                    onPetClicked = { pet ->
                        navController.navigate("pet_profile_screen/${pet.id}")
                    },
                    onEditClicked = { pet ->
                        navController.navigate("edit_pet_screen/${pet.id}")
                    },
                    onDeleteClicked = { pet ->
                        petViewModel.deletePet(pet)
                    }
                )
            }
            composable(Routes.ADD_PET_SCREEN) {
                // Pasa el viewModel a la pantalla
                AddPetScreen(
                    viewModel = petViewModel,
                    onPetAdded = { navController.popBackStack() }
                )
            }
            composable(
                route = Routes.PET_PROFILE_SCREEN,
                arguments = listOf(navArgument("petId") { type = NavType.IntType })
            ) { backStackEntry ->
                val petId = backStackEntry.arguments?.getInt("petId")
                // Usa el ViewModel para obtener la lista de mascotas y encontrar la correcta
                val allPets by petViewModel.allPets.collectAsState(initial = emptyList())
                val selectedPet = allPets.find { it.id == petId }

                if (selectedPet != null) {
                    PetProfileScreen(
                        pet = selectedPet,
                        onEditClicked = { petToEdit ->
                            navController.navigate("edit_pet_screen/${petToEdit.id}")
                        }
                    )
                }
            }
            composable(
                route = Routes.EDIT_PET_SCREEN,
                arguments = listOf(navArgument("petId") { type = NavType.IntType })
            ) { backStackEntry ->
                val petId = backStackEntry.arguments?.getInt("petId")
                // Usa el ViewModel para obtener la lista de mascotas y encontrar la correcta
                val allPets by petViewModel.allPets.collectAsState(initial = emptyList())
                val pet = allPets.find { it.id == petId }

                if (pet != null) {
                    EditPetScreen(
                        pet = pet,
                        onPetUpdated = { updatedPet ->
                            petViewModel.updatePet(updatedPet)
                            navController.popBackStack()
                        },
                        onPetDeleted = {
                            petViewModel.deletePet(pet)
                            navController.popBackStack()
                        }
                    )
                } else {
                    navController.popBackStack()
                }
            }
            composable(Routes.PROFILE_SCREEN) {
                UserProfileScreen()
            }
        }
    }
}