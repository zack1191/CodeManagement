package com.hha.codemanagement.data.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.hha.codemanagement.data.entities.Favorite
import com.hha.codemanagement.data.entities.MovieDetail
import com.hha.codemanagement.data.local.MovieDao
import com.hha.codemanagement.data.remote.MoviesDataSource
import com.hha.codemanagement.data.vo.Cast
import com.hha.codemanagement.utils.Resource
import com.hha.codemanagement.utils.performGetOperation
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val remoteDataSource : MoviesDataSource,
                                           private val localDataSource : MovieDao)
{

    fun getPopularMovies() = performGetOperation(
        databaseQuery = { localDataSource.getPopularMovies() },
        networkCall = { remoteDataSource.getPopularMovies() },
        saveCallResult = { localDataSource.insertPopularMovies(it.popularMovie) }
                                                )

    fun getUpcomingMovies() =
            performGetOperation(
                databaseQuery = { localDataSource.getUpcomingMovies() },
                networkCall = { remoteDataSource.getUpcomingMovies() },
                saveCallResult = { localDataSource.insertUpcomingMovies(it.upcomingMovies) }
                               )

    suspend fun getMovieDetail(id : String) : MovieDetail
    {
        return remoteDataSource.getMovieDetail(id)
    }

    suspend fun getCast(id : String) : Cast
    {
        return remoteDataSource.getCast(id)
    }

    @WorkerThread
    suspend fun insertFavorite(favorite : Favorite)
    {
        localDataSource.insertFavorite(favorite)
    }

    @WorkerThread
    suspend fun deleteFavorite(id : String)
    {
        localDataSource.deleteFavorite(id)
    }

    fun getFavorite(id : String) : LiveData<Favorite>
    {
        return localDataSource.getFavorite(id)
    }
}