package com.xhydracore.themoviedatabase.viewmodels

import com.xhydracore.themoviedatabase.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.repositories.TvShowRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val idTestTvShow = 63174
    private val idTestMovie = 412656

    @Before
    fun init() {
        viewModel = DetailViewModel()
    }

    @Test
    fun validateDataMovieWithId() {
        viewModel.id = idTestMovie
        val getMovieDetailData = viewModel.getMovieDetail()
        assertNotNull(getMovieDetailData)
        assertEquals(MoviesRepository.getMoviesData()[2], getMovieDetailData)
    }

    @Test
    fun validateDataTvShowWithId() {
        viewModel.id = idTestTvShow
        val getTvShowDetailData = viewModel.getTvShowDetail()
        assertNotNull(getTvShowDetailData)
        assertEquals(TvShowRepository.getTvShowData().last(), getTvShowDetailData)
    }

    @Test
    fun invalidIdMovie() {
        viewModel.id = 0
        assertNull(viewModel.getMovieDetail())
    }

    @Test
    fun invalidIdTvShow() {
        viewModel.id = 0
        assertNull(viewModel.getTvShowDetail())
    }
}