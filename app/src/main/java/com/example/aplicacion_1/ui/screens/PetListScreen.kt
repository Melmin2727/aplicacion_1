package com.example.aplicacion_1.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplicacion_1.model.Pet
import com.example.aplicacion_1.ui.components.PetCard
import com.example.aplicacion_1.ui.viewmodel.PetViewModel

@Composable
fun PetListScreen(
    viewModel: PetViewModel,
    onAddPetClicked: () -> Unit,
    onPetClicked: (Pet) -> Unit,
    onEditClicked: (Pet) -> Unit,
    onDeleteClicked: (Pet) -> Unit
) {
    // Obtiene la lista de mascotas del ViewModel como un estado
    val allPets by viewModel.allPets.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddPetClicked,
                containerColor = Color(0xFFD42E2D)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir mascota",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF4B3322))
                    .padding(paddingValues)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Mis Mascotas",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp, bottom = 16.dp, start = 16.dp)
                    )
                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(allPets) { pet ->
                            PetCard(
                                pet = pet,
                                onCardClick = { onPetClicked(pet) },
                                onEditClick = { onEditClicked(pet) },
                                onDeleteClick = { onDeleteClicked(pet) }
                            )
                        }
                    }
                }
            }
        }
    )
}