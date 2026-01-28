package com.example.marsphotosapp.data

//import com.example.marsphotosapp.network.MarsApi
import com.example.marsphotosapp.network.MarsApiService
import com.example.marsphotosapp.network.MarsPhoto

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>

}

class NetworkMarsPhotosRepository(
    private  val marsApiService: MarsApiService
) : MarsPhotosRepository{
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}