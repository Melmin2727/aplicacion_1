// Archivo: PetViewModel.kt
package com.example.aplicacion_1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.aplicacion_1.data.PetRepository
import com.example.aplicacion_1.model.Pet
import kotlinx.coroutines.launch

class PetViewModel(private val repository: PetRepository) : ViewModel() {
    val allPets = repository.allPets

    fun addPet(pet: Pet) {
        viewModelScope.launch {
            repository.insert(pet)
        }
    }

    fun updatePet(pet: Pet) {
        viewModelScope.launch {
            repository.update(pet)
        }
    }

    fun deletePet(pet: Pet) {
        viewModelScope.launch {
            repository.delete(pet)
        }
    }
}

class PetViewModelFactory(private val repository: PetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PetViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}