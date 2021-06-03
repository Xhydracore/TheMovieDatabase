package com.xhydracore.themoviedatabase.data.remote.models

import com.google.gson.annotations.SerializedName
import com.xhydracore.themoviedatabase.data.local.entities.GenreEntity

data class GenreResponse(
    @field:SerializedName("genres")
    val genres: List<GenreEntity>
)
