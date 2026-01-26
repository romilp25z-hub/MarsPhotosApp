package com.example.marsphotosapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsphotosapp.data.NetworkMarsPhotosRepository
import com.example.marsphotosapp.network.MarsApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class MarsViewModel : ViewModel() {

    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set


// private set for setters....
    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        viewModelScope.launch {

            try {
                val marsPhotosRepository = NetworkMarsPhotosRepository()
                val listResult =marsPhotosRepository.getMarsPhotos()
                marsUiState = MarsUiState.Success( "Success: ${listResult.size} Mars photos retrieved")
            } catch (e: IOException) {
                    marsUiState=MarsUiState.Error

            }

        }
    }
}
