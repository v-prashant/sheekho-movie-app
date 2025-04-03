package com.example.movies.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.api.ApiResult
import com.example.movies.domain.model.MovieDetailsItem
import com.example.movies.domain.use_case.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsVM @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val savedStatehandle: SavedStateHandle
): ViewModel() {

    private val _movieDetails = MutableStateFlow<ApiResult<MovieDetailsItem>>(ApiResult.Loading)
    val movieDetails = _movieDetails

    init {
        val id = savedStatehandle.get<Int>("id")
        getMovieDetails(id!!)
    }

    private fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase(id)
                .collectLatest { res ->
                    _movieDetails.value = res
                }
        }
    }

}