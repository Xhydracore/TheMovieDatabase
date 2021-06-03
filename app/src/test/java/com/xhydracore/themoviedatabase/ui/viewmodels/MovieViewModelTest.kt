package com.xhydracore.themoviedatabase.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.xhydracore.themoviedatabase.data.DataDummy
import com.xhydracore.themoviedatabase.data.remote.models.movie.ResultsMovie
import com.xhydracore.themoviedatabase.data.repositories.MoviesRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var observer: Observer<List<ResultsMovie>>

    @Before
    fun init() {
        viewModel = MovieViewModel(moviesRepository)
    }

    @Test
    fun validateDataInViewModelFromRepository() {
        val movieDataDummy = DataDummy.getPopularMovieDummy()
        val moviesLiveDataMock = MutableLiveData<List<ResultsMovie>>()
        moviesLiveDataMock.value = movieDataDummy

        `when`(moviesRepository.getMoviesData()).thenReturn(moviesLiveDataMock)
        val moviesData = viewModel.getMovies().value
        verify(moviesRepository).getMoviesData()
        assertNotNull(moviesData)
        assertEquals(moviesData?.size, movieDataDummy.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(movieDataDummy)
    }
}