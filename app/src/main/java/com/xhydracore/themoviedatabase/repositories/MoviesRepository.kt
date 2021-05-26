package com.xhydracore.themoviedatabase.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xhydracore.themoviedatabase.models.movie.ResultsMovie
import com.xhydracore.themoviedatabase.repositories.remote.RemoteRepositories

class MoviesRepository private constructor(private val remoteRepositories: RemoteRepositories) {
    companion object {
        val getInstance by lazy { MoviesRepository(RemoteRepositories.getInstance) }
    }

    fun getMoviesData(): LiveData<List<ResultsMovie>> {
        val moviesData = MutableLiveData<List<ResultsMovie>>()
    }
}