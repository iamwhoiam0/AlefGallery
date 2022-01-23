package com.example.alefgallery.data.api

import com.example.alefgallery.data.entities.GalleryList
import retrofit2.Response

interface ApiHelper {

    suspend fun getGalleryList(): Response<GalleryList>
}