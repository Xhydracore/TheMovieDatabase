package com.xhydracore.themoviedatabase.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.xhydracore.themoviedatabase.data.local.entities.MovieEntity
import com.xhydracore.themoviedatabase.data.local.entities.TvShowEntity
import com.xhydracore.themoviedatabase.data.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.data.repositories.TvShowRepository

class BookmarkViewModel(
    private val moviesRepository: MoviesRepository,
    private val tvShowRepository: TvShowRepository
) : ViewModel() {
    fun getBookmarkMovie(): LiveData<PagedList<MovieEntity>> =
        moviesRepository.getBookmarkDataMovie()

    fun getBookmarkTvShow(): LiveData<PagedList<TvShowEntity>> =
        tvShowRepository.getBookmarkTvShow()
}