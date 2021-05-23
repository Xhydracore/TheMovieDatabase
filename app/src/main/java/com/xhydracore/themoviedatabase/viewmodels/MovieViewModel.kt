package com.xhydracore.themoviedatabase.viewmodels

import androidx.lifecycle.ViewModel
import com.xhydracore.themoviedatabase.models.MoviesEntity
import com.xhydracore.themoviedatabase.repositories.MoviesRepository

class MovieViewModel : ViewModel() {
    internal fun getMovies(): ArrayList<MoviesEntity> = MoviesRepository.getMoviesData()
}