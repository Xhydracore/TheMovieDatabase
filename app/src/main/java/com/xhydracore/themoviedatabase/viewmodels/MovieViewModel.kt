package com.xhydracore.themoviedatabase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xhydracore.themoviedatabase.models.movie.ResultsMovie
import com.xhydracore.themoviedatabase.repositories.MoviesRepository

class MovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    internal fun getMovies(): LiveData<List<ResultsMovie>> = moviesRepository.getMoviesData()
}