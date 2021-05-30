package com.xhydracore.themoviedatabase.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.xhydracore.themoviedatabase.data.DataDummy
import com.xhydracore.themoviedatabase.models.tvshow.ResultsTvShow
import com.xhydracore.themoviedatabase.repositories.TvShowRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Mock
    private lateinit var observer: Observer<List<ResultsTvShow>>

    @Before
    fun init() {
        viewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun validateDataInViewModelFromRepository() {
        val tvShowsDataDummy = DataDummy.getPopularTvShowDummy()
        val tvShowsMock = MutableLiveData<List<ResultsTvShow>>()
        tvShowsMock.value = tvShowsDataDummy

        Mockito.`when`(tvShowRepository.getTvShowsData()).thenReturn(tvShowsMock)
        val tvShowData = viewModel.getTvShows().value
        verify(tvShowRepository).getTvShowsData()
        assertNotNull(tvShowData)
        assertEquals(tvShowData?.size, tvShowsDataDummy.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(tvShowsDataDummy)
    }
}