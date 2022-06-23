package com.mantelpiecesb.moviesapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mantelpiecesb.moviesapp.models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun getMoviesDao(): MovieDao

}
