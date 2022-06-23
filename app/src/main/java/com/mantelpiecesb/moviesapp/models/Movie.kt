package com.mantelpiecesb.moviesapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val headline: String,
    val display_title : String,
    val summary_short : String,
    val multimedia : Multimedia
) : Serializable
