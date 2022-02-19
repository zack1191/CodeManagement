package com.hha.codemanagement.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite")
data class Favorite(
        @PrimaryKey
        val id : String
                   )
