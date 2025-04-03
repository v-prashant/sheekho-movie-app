package com.example.movies.data.dto

import com.google.gson.annotations.SerializedName

data class AnimeImages(
    @SerializedName("jpg") val jpg: ImageDetails?
)