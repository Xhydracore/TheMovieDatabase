package com.xhydracore.themoviedatabase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xhydracore.themoviedatabase.models.movie.ResponseDetailMovie
import com.xhydracore.themoviedatabase.models.tvshow.ResponseDetailTvShow
import com.xhydracore.themoviedatabase.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.repositories.TvShowRepository

class DetailViewModel(
    private val moviesRepository: MoviesRepository,
    private val tvShowRepository: TvShowRepository,
) : ViewModel() {
    var id: Int = 0

    fun getMovieDetail(): LiveData<ResponseDetailMovie> {
        return moviesRepository.getDetailMoviesData(id)
    }

    fun getTvShowDetail(): LiveData<ResponseDetailTvShow> {
        return tvShowRepository.getTvShowDetailData(id)
    }
}