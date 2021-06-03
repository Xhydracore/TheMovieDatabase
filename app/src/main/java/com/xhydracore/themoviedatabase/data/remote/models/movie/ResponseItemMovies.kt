package com.xhydracore.themoviedatabase.data.remote.models.movie

import com.google.gson.annotations.SerializedName
import com.xhydracore.themoviedatabase.data.local.entities.MovieEntity

data class ResponseItemMovies(
    @field:SerializedName("results")
    val results: List<MovieEntity>,
)