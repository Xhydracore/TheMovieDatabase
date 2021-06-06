package com.xhydracore.themoviedatabase.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.xhydracore.themoviedatabase.data.local.entities.MovieEntity
import com.xhydracore.themoviedatabase.data.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.vo.ResourceValue
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
    private lateinit var pagedList: PagedList<MovieEntity>

    @Mock
    private lateinit var observer: Observer<ResourceValue<PagedList<MovieEntity>>>

    @Before
    fun init() {
        viewModel = MovieViewModel(moviesRepository)
    }

    @Test
    fun validateDataInViewModelFromRepository() {
        val movieDataDummy = ResourceValue.success(pagedList)
        `when`(movieDataDummy.data?.size).thenReturn(10)
        val moviesLiveDataMock = MutableLiveData<ResourceValue<PagedList<MovieEntity>>>()
        moviesLiveDataMock.value = movieDataDummy

        `when`(moviesRepository.getMoviesData()).thenReturn(moviesLiveDataMock)
        val moviesData = viewModel.getMovies().value?.data
        verify(moviesRepository).getMoviesData()
        assertNotNull(moviesData)
        assertEquals(10, moviesData?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(movieDataDummy)
    }
}