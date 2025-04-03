package com.example.movies.domain.model

data class MovieDetailsItem(
    val videoUrl: String?,
    val videoId: String?,
    val posterImageUrl: String?,
    val title: String?,
    val synopsis: String?,
    val genres: List<String?>?,
    val mainCast: String?,
    val noOfEpisodes: String?,
    val rating: String?,
)
