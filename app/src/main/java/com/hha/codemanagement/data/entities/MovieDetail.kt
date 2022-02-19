package com.hha.codemanagement.data.entities

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieDetail")
data class MovieDetail(
        val adult : Boolean,
        val backdrop_path : String,
        val budget : Int,
        val homepage : String,
        @PrimaryKey(autoGenerate = true)
        val id : Int,
        val imdb_id : String,
        val original_language : String,
        val original_title : String,
        val overview : String,
        val popularity : Double,
        @ColumnInfo(name = "poster-path")
        val poster_path : String,

        val release_date : String,
        val revenue : Int,
        val runtime : Int,
        val status : String,
        val tagline : String,
        @ColumnInfo(name = "title")
        val title : String,
        val video : Boolean,
        val vote_average : Double,
        val vote_count : Int
                      )