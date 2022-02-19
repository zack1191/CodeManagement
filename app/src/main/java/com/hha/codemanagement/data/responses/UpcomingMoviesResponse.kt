package com.hha.codemanagement.data.responses

import com.google.gson.annotations.SerializedName
import com.hha.codemanagement.data.entities.PopularMovie
import com.hha.codemanagement.data.entities.UpcomingMovie

data class UpcomingMoviesResponse(
        val page : Int,
        @SerializedName("results")
        val upcomingMovies : List<UpcomingMovie>,
        val total_pages : Int,
        val total_results : Int
                                 )