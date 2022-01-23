package com.example.alefgallery.data.repository

import com.example.alefgallery.data.api.ApiHelper

class MainRepository (private val apiHelper: ApiHelper){

    suspend fun getGalleryList() = apiHelper.getGalleryList()

}