package com.example.movies.data.dto

import com.example.movies.domain.model.MovieDetailsItem
import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    val data: MovieDetails? = null
)

data class MovieDetails(
    @SerializedName("mal_id") val id: Int?,
    val title: String?,
    val synopsis: String?,
    val score: Float?,
    val episodes: Int?,
    val genres: List<Genre>?,
    val trailer: Trailer?,
    val images: AnimeImages?,
    val rating: String?
)

fun MovieDetails.toMovieDetailsItem(): MovieDetailsItem {
    return MovieDetailsItem(
        videoUrl = trailer?.embedUrl,
        videoId = trailer?.youtubeId,
        posterImageUrl = images?.jpg?.imageUrl,
        title = title,
        synopsis = synopsis,
        genres = genres?.map { it.name },
        mainCast = score?.toString(),
        noOfEpisodes = episodes?.toString(),
        rating = score?.toString()
    )
}

