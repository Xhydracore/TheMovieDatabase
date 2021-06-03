package com.xhydracore.themoviedatabase.data.remote.models.tvshow

import com.google.gson.annotations.SerializedName
import com.xhydracore.themoviedatabase.data.local.entities.TvShowEntity

data class ResponseItemTvShows(
    @field:SerializedName("results")
    val results: List<TvShowEntity>,
)