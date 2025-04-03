package com.example.movies.data.repository

import com.example.movies.api.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService)  {
    suspend fun getMovies(pageId: Int) = apiService.getMovies(pageId)
    suspend fun getMovieDetails(animeId: Int) = apiService.getMovieDetails(animeId)
}