package com.example.movies.data.dto

import com.google.gson.annotations.SerializedName

data class ImageDetails(
    @SerializedName("image_url") val imageUrl: String?
)