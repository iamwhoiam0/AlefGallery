package com.example.alefgallery.data.api

import com.example.alefgallery.data.api.ApiService
import com.example.alefgallery.data.entities.GalleryList
import retrofit2.Response

class ApiHelperImpl (private val apiService: ApiService) : ApiHelper {

    override suspend fun getGalleryList(): Response<GalleryList> = apiService.getGalleryList()

}