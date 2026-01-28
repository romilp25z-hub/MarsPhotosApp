package com.example.marsphotosapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotosapp.MarsPhotosApplication
import com.example.marsphotosapp.data.MarsPhotosRepository
import com.example.marsphotosapp.data.NetworkMarsPhotosRepository
import com.example.marsphotosapp.network.MarsApiService
import com.example.marsphotosapp.network.MarsPhoto
//import com.example.marsphotosapp.network.MarsApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photos: MarsPhoto) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class MarsViewModel(
    private  val marsPhotosRepository: MarsPhotosRepository
) : ViewModel() {

    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set


// private set for setters....
    init {
        getMarsPhotos()
    }

    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = MarsUiState.Loading
            marsUiState = try {
//                val listResult = marsPhotosRepository.getMarsPhotos()
                val result = marsPhotosRepository.getMarsPhotos()[0]
//                MarsUiState.Success(
//                    "Success: ${listResult.size} Mars photos retrieved"
//                )
                MarsUiState.Success(marsPhotosRepository.getMarsPhotos()[0])

            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}
