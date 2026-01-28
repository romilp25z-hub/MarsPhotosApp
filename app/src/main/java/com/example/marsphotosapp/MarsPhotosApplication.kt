package com.example.marsphotosapp

import android.app.Application
import com.example.marsphotosapp.data.AppContainer
import com.example.marsphotosapp.data.DefaultAppContainer

class MarsPhotosApplication () :Application(){
    lateinit var container : AppContainer
    override fun onCreate() {
        super.onCreate()
        container=DefaultAppContainer()
    }
}