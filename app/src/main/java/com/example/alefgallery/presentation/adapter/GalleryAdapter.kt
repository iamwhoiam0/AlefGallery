package com.example.alefgallery.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alefgallery.R
import com.squareup.picasso.Picasso
import kotlin.coroutines.coroutineContext

class GalleryAdapter(): ListAdapter<List<String>, GalleryAdapter.GalleryViewHolder>(
object : DiffUtil.ItemCallback<List<String>>() {
    override fun areItemsTheSame(oldItem: List<String>, newItem: List<String>): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: List<String>, newItem: List<String>): Boolean =
        oldItem == newItem
}
) {

    class GalleryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemImage1: ImageView = itemView.findViewById(R.id.item1)
        val itemImage2: ImageView = itemView.findViewById(R.id.item2)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val urls = getItem(position)?.map { s -> s.replace("http:", "https:")}
        if (urls != null){
            Picasso.get()
                .load(urls[0])
                .placeholder(R.drawable.empty_image)
                .fit()
                .into(holder.itemImage1)
            Picasso.get()
                .load(urls[1])
                .placeholder(R.drawable.empty_image)
                .fit()
                .into(holder.itemImage2)


            if (holder.itemImage1.context.resources.getBoolean(R.bool.isTablet)){
                val itemImage3: ImageView = holder.itemView.findViewById(R.id.item3)
                Picasso.get()
                    .load(urls[2])
                    .placeholder(R.drawable.empty_image)
                    .fit()
                    .into(itemImage3)
            }
        }
    }
}