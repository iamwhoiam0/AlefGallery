package com.example.alefgallery.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alefgallery.R
import com.example.alefgallery.data.entities.GalleryList
import com.example.alefgallery.databinding.ActivityMainBinding
import com.example.alefgallery.presentation.adapter.GalleryAdapter
import com.example.alefgallery.presentation.viewModel.MainViewModel
import com.example.alefgallery.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModel<MainViewModel>()

    private lateinit var galleryAdapter: GalleryAdapter
    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.gallerySrl.setOnRefreshListener {
            galleryAdapter.submitList(emptyList())
            mainViewModel.refreshGalleryList()
        }

        setupAdapter()
        setupObservers()
    }

    private fun setupAdapter(){
        galleryAdapter = GalleryAdapter()
        mBinding.galleryRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBinding.galleryRv.adapter = galleryAdapter
    }

    private fun setupObservers() {
        mainViewModel.galleryItem.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.i("Success", it.data.toString())
                    it.data?.let { main ->
                        retrieveList(main)
                    }
                    Toast.makeText(this, "Загрузка завершена", Toast.LENGTH_SHORT).show()
                    mBinding.gallerySrl.isRefreshing = false
                }
                Status.LOADING -> {
                    Toast.makeText(this, "Загрузка данных", Toast.LENGTH_SHORT).show()
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
    private fun retrieveList(galleryListData: GalleryList){
        val size = if (resources.getBoolean(R.bool.isTablet)) 3 else 2
        val rows = galleryListData.urls.chunked(size)
        galleryAdapter.submitList(rows)
    }
}