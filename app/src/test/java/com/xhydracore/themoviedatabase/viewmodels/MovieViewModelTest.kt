package com.xhydracore.themoviedatabase.viewmodels

import com.xhydracore.themoviedatabase.models.MoviesEntity
import com.xhydracore.themoviedatabase.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.repositories.TvShowRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel
    private lateinit var moviesList: ArrayList<MoviesEntity>

    @Before
    fun init() {
        viewModel = MovieViewModel()
        moviesList = viewModel.getMovies()
    }

    @Test
    fun validateSizeInViewModelFromRepository() {
        assertNotNull(moviesList)
        assertEquals(10, moviesList.size)
    }

    @Test
    fun validateDataInViewModelFromRepository() {
        assertEquals(moviesList, MoviesRepository.getMoviesData())
        assertFalse(moviesList == TvShowRepository.getTvShowData())
    }
}