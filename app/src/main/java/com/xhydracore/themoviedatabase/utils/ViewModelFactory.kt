package com.xhydracore.themoviedatabase.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xhydracore.themoviedatabase.data.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.data.repositories.TvShowRepository
import com.xhydracore.themoviedatabase.ui.viewmodels.BookmarkViewModel
import com.xhydracore.themoviedatabase.ui.viewmodels.DetailViewModel
import com.xhydracore.themoviedatabase.ui.viewmodels.MovieViewModel
import com.xhydracore.themoviedatabase.ui.viewmodels.TvShowViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactoryMovie(private val moviesRepository: MoviesRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MovieViewModel(moviesRepository) as T
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactoryTvShows(private val tvShowRepository: TvShowRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        TvShowViewModel(tvShowRepository) as T
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactoryDetail(
    private val moviesRepository: MoviesRepository,
    private val tvShowRepository: TvShowRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DetailViewModel(moviesRepository, tvShowRepository) as T
}

@Suppress("UNCHECKED_CAST")
class ViewModelFactoryBookmark(
    private val moviesRepository: MoviesRepository,
    private val tvShowRepository: TvShowRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        BookmarkViewModel(moviesRepository, tvShowRepository) as T
}