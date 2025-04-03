package com.example.movies.data.dto

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("name") val name: String?
)