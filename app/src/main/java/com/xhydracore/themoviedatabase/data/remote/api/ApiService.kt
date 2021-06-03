package com.xhydracore.themoviedatabase.data.remote.api

import com.xhydracore.themoviedatabase.data.remote.models.GenreResponse
import com.xhydracore.themoviedatabase.data.remote.models.movie.ResponseItemMovies
import com.xhydracore.themoviedatabase.data.remote.models.tvshow.ResponseItemTvShows
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovie(@Query("api_key") apiKey: String = ApiKey.API_KEY): Call<ResponseItemMovies>

    @GET("tv/popular")
    fun getPopularTvShow(@Query("api_key") apiKey: String = ApiKey.API_KEY): Call<ResponseItemTvShows>

    @GET("genre/movie/list")
    fun getGenreMovie(@Query("api_key") apiKey: String = ApiKey.API_KEY): Call<GenreResponse>

    @GET("genre/tv/list")
    fun getGenreTv(@Query("api_key") apiKey: String = ApiKey.API_KEY): Call<GenreResponse>
}