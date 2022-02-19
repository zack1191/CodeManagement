package com.hha.codemanagement.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hha.codemanagement.data.entities.Favorite
import com.hha.codemanagement.data.entities.MovieDetail
import com.hha.codemanagement.data.entities.PopularMovie
import com.hha.codemanagement.data.entities.UpcomingMovie

@Database(entities = [PopularMovie::class, UpcomingMovie::class, MovieDetail::class, Favorite::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun movieDao() : MovieDao

    companion object
    {
        @Volatile private var instance : AppDatabase? = null

        fun getDatabase(context : Context) : AppDatabase =
                instance ?: synchronized(this) {
                    instance ?: buildDatabase(context).also { instance = it }
                }

        private fun buildDatabase(appContext : Context) =
                Room.databaseBuilder(appContext, AppDatabase::class.java, "MovieDatabase.db")
                        .build()
    }
}
