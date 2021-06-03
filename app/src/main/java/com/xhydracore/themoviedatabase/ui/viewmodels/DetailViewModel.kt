package com.xhydracore.themoviedatabase.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xhydracore.themoviedatabase.data.local.entities.GenreEntity
import com.xhydracore.themoviedatabase.data.local.entities.MovieEntity
import com.xhydracore.themoviedatabase.data.local.entities.TvShowEntity
import com.xhydracore.themoviedatabase.data.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.data.repositories.TvShowRepository
import com.xhydracore.themoviedatabase.vo.ResourceValue

class DetailViewModel(
    private val moviesRepository: MoviesRepository,
    private val tvShowRepository: TvShowRepository,
) : ViewModel() {
    var id: Int = 0

    fun getGenreMovie(): LiveData<ResourceValue<List<GenreEntity>>> =
        moviesRepository.getGenreMovie()

    fun checkBookmarkMovie(movieId: Int): LiveData<Boolean> =
        moviesRepository.checkBookmarkMovie(movieId)

    fun setBookmarkMovie(movieEntity: MovieEntity, status: Boolean) =
        moviesRepository.setBookmarkMovie(movieEntity, status)

    fun getGenreTvShow(): LiveData<ResourceValue<List<GenreEntity>>> =
        tvShowRepository.getGenreTvShow()

    fun checkBookmarkTvShow(tvShowId: Int): LiveData<Boolean> =
        tvShowRepository.checkBookmarkTvShow(tvShowId)

    fun setBookmarkTvShow(tvShowEntity: TvShowEntity, status: Boolean) =
        tvShowRepository.setBookmarkTvShow(tvShowEntity, status)
}