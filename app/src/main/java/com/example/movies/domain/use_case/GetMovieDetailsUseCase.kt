package com.example.movies.domain.use_case

import com.example.movies.api.ApiResult
import com.example.movies.data.dto.toMovieDetailsItem
import com.example.movies.data.repository.MovieRepository
import com.example.movies.domain.model.MovieDetailsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repo: MovieRepository) {

    operator fun invoke(pageId: Int): Flow<ApiResult<MovieDetailsItem>> = flow {
        try {
            emit(ApiResult.Loading)
            val res = repo.getMovieDetails(pageId)
            if (res.isSuccessful && res.body()?.data != null)
                emit(ApiResult.Success(res.body()!!.data!!.toMovieDetailsItem()))
            else
                emit(ApiResult.Error(res.message()))
        } catch (e: HttpException) {
            emit(ApiResult.Error("Unknown Error"))
        } catch (e: IOException) {
            emit(ApiResult.Error("Check Internet Connection"))
        }
    }.flowOn(Dispatchers.IO)

}