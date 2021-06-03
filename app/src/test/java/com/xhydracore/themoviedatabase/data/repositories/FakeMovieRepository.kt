package com.xhydracore.themoviedatabase.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xhydracore.themoviedatabase.data.remote.models.movie.ResponseDetailMovie
import com.xhydracore.themoviedatabase.data.remote.models.movie.ResultsMovie
import com.xhydracore.themoviedatabase.data.repositories.remote.GetDetailMovieCallback
import com.xhydracore.themoviedatabase.data.repositories.remote.GetMoviesCallback
import com.xhydracore.themoviedatabase.data.repositories.remote.RemoteRepositories

class FakeMovieRepository(private val remoteRepositories: RemoteRepositories) {

    fun getMoviesData(): LiveData<List<ResultsMovie>> {
        val moviesData = MutableLiveData<List<ResultsMovie>>()
        remoteRepositories.getPopularMovies(object : GetMoviesCallback {
            override fun onResponse(responseMovies: List<ResultsMovie>) {
                moviesData.postValue(responseMovies)
            }
        })
        return moviesData
    }

    fun getDetailMoviesData(movieId: Int): LiveData<ResponseDetailMovie> {
        val movieDetailData = MutableLiveData<ResponseDetailMovie>()
        remoteRepositories.getDetailMovie(movieId, object : GetDetailMovieCallback {
            override fun onResponse(responseDetailMovie: ResponseDetailMovie) {
                movieDetailData.postValue(responseDetailMovie)
            }
        })
        return movieDetailData
    }
}