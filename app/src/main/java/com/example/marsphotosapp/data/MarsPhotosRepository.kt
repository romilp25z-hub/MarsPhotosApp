package com.example.marsphotosapp.data

import com.example.marsphotosapp.network.MarsApi
import com.example.marsphotosapp.network.MarsPhoto

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>

}

class NetworkMarsPhotosRepository() : MarsPhotosRepository{
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return MarsApi.retrofitService.getPhotos()
    }
}