package com.xhydracore.themoviedatabase.models.tvshow

import com.google.gson.annotations.SerializedName

data class ResponseItemTvShows(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<ResultsTvShow>,

	@field:SerializedName("total_results")
	val totalResults: Int
)