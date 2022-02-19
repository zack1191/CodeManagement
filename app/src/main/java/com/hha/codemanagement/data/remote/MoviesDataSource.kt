package com.hha.codemanagement.data.remote

import androidx.lifecycle.LiveData
import com.hha.codemanagement.data.entities.MovieDetail
import com.hha.codemanagement.utils.Resource
import javax.inject.Inject

class MoviesDataSource @Inject constructor(private val moviesService : MoviesService) : BaseDataSource()
{

    suspend fun getPopularMovies() = getResult { moviesService.getPopularMovies() }
    suspend fun getUpcomingMovies() = getResult { moviesService.getUpcoming() }
    suspend fun getMovieDetail(id : String) = moviesService.getDetail(id)
    suspend fun getCast(id : String) = moviesService.getCast(id)

}