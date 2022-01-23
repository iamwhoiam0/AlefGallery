package com.example.alefgallery.presentation.di.module

import com.example.alefgallery.data.api.ApiHelper
import com.example.alefgallery.data.api.ApiHelperImpl
import com.example.alefgallery.data.api.ApiService
import com.example.alefgallery.data.converters.GalleryListConverter
import com.example.alefgallery.data.entities.GalleryList
import com.example.alefgallery.data.repository.MainRepository
import com.example.alefgallery.presentation.viewModel.MainViewModel
import com.example.alefgallery.utils.Constants
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private var converterFactory: GsonConverterFactory? = null

val mainModule = module{

    single { provideRetrofit(Constants.BASE_URL) }
    factory { provideApiService(get()) }

    factory<ApiHelper> {
        return@factory ApiHelperImpl(get())
    }

    single {
        MainRepository(get())
    }

    viewModel {
        MainViewModel(get())
    }
}

private fun provideRetrofit(BASE_URL: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(customConverter())
        //.addConverterFactory(ScalarsConverterFactory.create())
        .build()
}
private fun customConverter(): GsonConverterFactory =
    converterFactory ?: run {
        converterFactory = GsonConverterFactory.create(
        GsonBuilder()
            .registerTypeAdapter(GalleryList::class.java, GalleryListConverter)
            .create()
        )
        converterFactory!!
    }

private fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)