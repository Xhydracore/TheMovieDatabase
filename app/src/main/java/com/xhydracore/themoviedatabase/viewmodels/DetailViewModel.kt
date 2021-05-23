package com.xhydracore.themoviedatabase.viewmodels

import androidx.lifecycle.ViewModel
import com.xhydracore.themoviedatabase.models.MoviesEntity
import com.xhydracore.themoviedatabase.models.TvShowEntity
import com.xhydracore.themoviedatabase.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.repositories.TvShowRepository

class DetailViewModel : ViewModel() {
    var id: Int = 0

    fun getMovieDetail(): MoviesEntity? {
        val list = MoviesRepository.getMoviesData()
        return list.find { it.movieId == id }
    }

    fun getTvShowDetail(): TvShowEntity? {
        val list = TvShowRepository.getTvShowData()
        return list.find { it.tvShowId == id }
    }
}