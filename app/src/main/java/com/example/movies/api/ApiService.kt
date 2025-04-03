package com.example.movies.api

import com.example.movies.api.constants.ApiConstant.MOVIE_DETAILS_END_POINT
import com.example.movies.api.constants.ApiConstant.MOVIE_LIST_END_POINT
import com.example.movies.data.dto.MovieDetailsResponse
import com.example.movies.data.dto.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // "https://api.jikan.moe/v4/top/anime?page=1"
    @GET(MOVIE_LIST_END_POINT)
    suspend fun getMovies(@Query("page") pageId: Int): Response<MovieListResponse>

    // "https://api.jikan.moe/v4/anime/52991"
    @GET(MOVIE_DETAILS_END_POINT)
    suspend fun getMovieDetails(@Path("anime_id") animeId: Int): Response<MovieDetailsResponse>

}