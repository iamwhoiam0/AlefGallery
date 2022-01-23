package com.example.alefgallery.data.api

import com.example.alefgallery.data.entities.GalleryList
import com.example.alefgallery.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.API_GALLERY)
    suspend fun getGalleryList(): Response<GalleryList>
}