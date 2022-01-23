package com.example.alefgallery.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alefgallery.data.entities.GalleryList
import com.example.alefgallery.data.repository.MainRepository
import com.example.alefgallery.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel (
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _galleryItem = MutableLiveData<Resource<GalleryList>>()
    val galleryItem: LiveData<Resource<GalleryList>>
        get() = _galleryItem

    init {
        getGalleryList()
    }

    fun refreshGalleryList(){
        getGalleryList()
    }

    private fun getGalleryList() {
        viewModelScope.launch {
            _galleryItem.postValue(Resource.loading(null))
            mainRepository.getGalleryList().let {
                if (it.isSuccessful){
                    _galleryItem.postValue(Resource.success(it.body()))
                } else{
                    _galleryItem.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }
}