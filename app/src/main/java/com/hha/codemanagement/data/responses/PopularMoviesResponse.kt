package com.hha.codemanagement.data.responses

import com.google.gson.annotations.SerializedName
import com.hha.codemanagement.data.entities.PopularMovie

data class PopularMovieResponse(
        val page: Int,
        @SerializedName("results")
        val popularMovie: List<PopularMovie>,
        val total_pages: Int,
        val total_results: Int
                               )