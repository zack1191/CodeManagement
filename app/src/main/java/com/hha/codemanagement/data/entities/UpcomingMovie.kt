package com.hha.codemanagement.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UpcomingMovies")
data class UpcomingMovie(
        @ColumnInfo(name = "adult") val adult : Boolean,
        @ColumnInfo(name = "backdrop-path") val backdrop_path : String,


        @PrimaryKey val id : Int,
        @ColumnInfo(name = "original-language") val original_language : String,
        @ColumnInfo(name = "original-title") val original_title : String,
        @ColumnInfo(name = "overview") val overview : String,
        @ColumnInfo(name = "popularity") val popularity : Double,
        @ColumnInfo(name = "poster-path") val poster_path : String?,
        @ColumnInfo(name = "release-date") val release_date : String,
        @ColumnInfo(name = "title") val title : String,
        @ColumnInfo(name = "video") val video : Boolean,
        @ColumnInfo(name = "vote-average") val vote_average : Double,
        @ColumnInfo(name = "vote-count") val vote_count : Int
                       )