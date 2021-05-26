package com.xhydracore.themoviedatabase.repositories.remote

import android.util.Log
import com.xhydracore.themoviedatabase.api.ApiConfig
import com.xhydracore.themoviedatabase.api.ApiKey
import com.xhydracore.themoviedatabase.models.movie.ResponseDetailMovie
import com.xhydracore.themoviedatabase.models.movie.ResponseItemMovies
import com.xhydracore.themoviedatabase.models.movie.ResultsMovie
import com.xhydracore.themoviedatabase.models.tvshow.ResponseDetailTvShow
import com.xhydracore.themoviedatabase.models.tvshow.ResponseItemTvShows
import com.xhydracore.themoviedatabase.models.tvshow.ResultsTvShow
import com.xhydracore.themoviedatabase.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepositories(private val myApiConfig: ApiConfig) {
    companion object {
        private const val TAG = "RemoteRepositories"
        private const val API_KEY = ApiKey.API_KEY
        val getInstance by lazy { RemoteRepositories(ApiConfig) }
    }

    fun getPopularMovies(getMoviesCallback: GetMoviesCallback) {
        EspressoIdlingResource.increment()
        myApiConfig.apiService.getPopularMovie(API_KEY)
            .enqueue(object : Callback<ResponseItemMovies> {
                override fun onResponse(
                    call: Call<ResponseItemMovies>,
                    response: Response<ResponseItemMovies>
                ) {
                    getMoviesCallback.onResponse(response.body()?.results as List<ResultsMovie>)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<ResponseItemMovies>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.printStackTrace()}")
                }
            })
    }

    fun getDetailMovie(movieId: Int, getDetailMovieCallback: GetDetailMovieCallback) {
        EspressoIdlingResource.increment()
        myApiConfig.apiService.getDetailMovie(movieId, API_KEY)
            .enqueue(object : Callback<ResponseDetailMovie> {
                override fun onResponse(
                    call: Call<ResponseDetailMovie>,
                    response: Response<ResponseDetailMovie>
                ) {
                    getDetailMovieCallback.onResponse(response.body() as ResponseDetailMovie)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.printStackTrace()}")
                }
            })
    }

    fun getPopularTvShows(getTvShowsCallback: GetTvShowsCallback) {
        EspressoIdlingResource.increment()
        myApiConfig.apiService.getPopularTvShow(API_KEY)
            .enqueue(object : Callback<ResponseItemTvShows> {
                override fun onResponse(
                    call: Call<ResponseItemTvShows>,
                    response: Response<ResponseItemTvShows>
                ) {
                    getTvShowsCallback.onResponse(response.body()?.results as List<ResultsTvShow>)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<ResponseItemTvShows>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.printStackTrace()}")
                }
            })
    }

    fun getDetailTvShow(tvShowId: Int, getDetailTvShowCallback: GetDetailTvShowCallback) {
        EspressoIdlingResource.increment()
        myApiConfig.apiService.getDetailTvShow(tvShowId, API_KEY)
            .enqueue(object : Callback<ResponseDetailTvShow> {
                override fun onResponse(
                    call: Call<ResponseDetailTvShow>,
                    response: Response<ResponseDetailTvShow>
                ) {
                    getDetailTvShowCallback.onResponse(response.body() as ResponseDetailTvShow)
                    EspressoIdlingResource.decrement()
                }

                override fun onFailure(call: Call<ResponseDetailTvShow>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.printStackTrace()}")
                }
            })

    }
}

interface GetMoviesCallback {
    fun onResponse(responseMovies: List<ResultsMovie>)
}

interface GetTvShowsCallback {
    fun onResponse(responseTvShows: List<ResultsTvShow>)
}

interface GetDetailMovieCallback {
    fun onResponse(responseDetailMovie: ResponseDetailMovie)
}

interface GetDetailTvShowCallback {
    fun onResponse(responseDetailTvShow: ResponseDetailTvShow)
}