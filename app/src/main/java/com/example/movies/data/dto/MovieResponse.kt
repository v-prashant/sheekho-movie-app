package com.example.movies.data.dto

import com.example.movies.domain.model.MovieItem
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val data: List<MovieList>? = null
)

data class MovieList(
    @SerializedName("mal_id") val id: Int?,
    val title: String?,
    val images: AnimeImages?,
    val score: Float?,
    val episodes: Int?
)

fun MovieList.toMovieItem(): MovieItem {
    return MovieItem(
        id = id,
        title = title,
        noOfEpisodes = episodes?.toString(),
        rating = score?.toString(),
        posterImageUrl = images?.jpg?.imageUrl
    )
}