package com.xhydracore.themoviedatabase.models.movie

import com.google.gson.annotations.SerializedName

data class ResponseItemMovies(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<ResultsMovie>,

	@field:SerializedName("total_results")
	val totalResults: Int
)