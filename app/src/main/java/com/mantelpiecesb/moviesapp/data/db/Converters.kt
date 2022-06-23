package com.mantelpiecesb.moviesapp.data.db

import androidx.room.TypeConverter
import com.mantelpiecesb.moviesapp.models.Multimedia

class Converters {
    @TypeConverter
    fun fromMultimediaObj(value: Multimedia): String {
        return value.src
    }

    @TypeConverter
    fun toMultimediaObj(value: String): Multimedia {
        val multimediaObj = Multimedia(value)
        return multimediaObj
    }
}