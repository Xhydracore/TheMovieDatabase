package com.xhydracore.themoviedatabase.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.xhydracore.themoviedatabase.data.local.entities.MovieEntity
import com.xhydracore.themoviedatabase.data.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.vo.ResourceValue

class MovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    internal fun getMovies(): LiveData<ResourceValue<PagedList<MovieEntity>>> =
        moviesRepository.getMoviesData()
}