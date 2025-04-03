package com.example.movies.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.api.ApiResult
import com.example.movies.domain.model.MovieItem
import com.example.movies.domain.use_case.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListVM @Inject constructor(private val getMovieListUseCase: GetMovieListUseCase): ViewModel() {

    private val _movieList = MutableStateFlow<ApiResult<List<MovieItem>>>(ApiResult.Loading)
    val movieList: Flow<ApiResult<List<MovieItem>>> = _movieList

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            getMovieListUseCase(1)
                .collectLatest { res ->
                    _movieList.value = res
                }
        }
    }

}