package com.example.alefgallery.data.converters

import com.example.alefgallery.data.entities.GalleryList
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

object GalleryListConverter: JsonDeserializer<GalleryList> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GalleryList {
        val jsonArray = json as JsonArray
        val result = jsonArray.map { it.asString }

        return GalleryList(result)
    }
}