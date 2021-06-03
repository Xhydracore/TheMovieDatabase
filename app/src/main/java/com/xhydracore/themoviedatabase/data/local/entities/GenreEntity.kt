package com.xhydracore.themoviedatabase.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genre_entities")
data class GenreEntity(
    @PrimaryKey(autoGenerate = true)
    val primaryId: Int,

    @field:SerializedName("id")
    val genreId: Int,

    @field:SerializedName("name")
    val name: String,

    var type: String,
)
