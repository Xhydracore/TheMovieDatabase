package com.xhydracore.themoviedatabase.viewmodels

import com.xhydracore.themoviedatabase.models.TvShowEntity
import com.xhydracore.themoviedatabase.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.repositories.TvShowRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowsList: ArrayList<TvShowEntity>

    @Before
    fun init() {
        viewModel = TvShowViewModel()
        tvShowsList = viewModel.getTvShows()
    }

    @Test
    fun validateSizeInViewModelFromRepository() {
        assertNotNull(tvShowsList)
        assertEquals(10, tvShowsList.size)
    }

    @Test
    fun validateDataInViewModelFromRepository() {
        assertEquals(tvShowsList[1], TvShowRepository.getTvShowData()[1])
        assertFalse(tvShowsList == MoviesRepository.getMoviesData())
    }
}