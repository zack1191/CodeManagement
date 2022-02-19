package com.hha.codemanagement.data.remote

import com.hha.codemanagement.data.entities.MovieDetail
import com.hha.codemanagement.data.responses.PopularMovieResponse
import com.hha.codemanagement.data.responses.UpcomingMoviesResponse
import com.hha.codemanagement.data.vo.Cast
import com.hha.codemanagement.utils.Utils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesService
{

    @GET("movie/popular?api_key=${Utils.APIKEY}")
    suspend fun getPopularMovies() : Response<PopularMovieResponse>

    @GET("movie/upcoming?api_key=${Utils.APIKEY}")
    suspend fun getUpcoming() : Response<UpcomingMoviesResponse>


    @GET("movie/{movie_id}?api_key=${Utils.APIKEY}")
    suspend fun getDetail(@Path("movie_id") movieId : String) : MovieDetail

    @GET("movie/{movie_id}/credits?api_key=${Utils.APIKEY}")
    suspend fun getCast(@Path("movie_id") movieId : String):Cast
}