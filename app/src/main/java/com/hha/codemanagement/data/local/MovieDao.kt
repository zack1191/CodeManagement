package com.hha.codemanagement.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hha.codemanagement.data.entities.Favorite
import com.hha.codemanagement.data.entities.MovieDetail
import com.hha.codemanagement.data.entities.PopularMovie
import com.hha.codemanagement.data.entities.UpcomingMovie

@Dao
interface MovieDao
{
    @Query("SELECT * FROM PopularMovies")
    fun getPopularMovies() : LiveData<List<PopularMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(popularMovies : List<PopularMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingMovies(upcomingMovie : List<UpcomingMovie>)

    @Query("SELECT * FROM UpcomingMovies")
    fun getUpcomingMovies() : LiveData<List<UpcomingMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieDetail : MovieDetail)

    @Query("SELECT * FROM MovieDetail")
    fun getMovieDetail() : LiveData<MovieDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite : Favorite) : Long

    @Query("Delete from Favorite where id=:movieId")
    suspend fun deleteFavorite(movieId : String)

    @Query("Select * From Favorite where id=:movieId")
    fun getFavorite(movieId : String) : LiveData<Favorite>
}