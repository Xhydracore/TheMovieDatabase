package com.xhydracore.themoviedatabase.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.xhydracore.themoviedatabase.data.local.entities.TvShowEntity
import com.xhydracore.themoviedatabase.data.repositories.TvShowRepository
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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Mock
    private lateinit var observer: Observer<ResourceValue<PagedList<TvShowEntity>>>

    @Before
    fun init() {
        viewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun validateDataInViewModelFromRepository() {
        val tvShowsDataDummy = ResourceValue.success(pagedList)
        `when`(tvShowsDataDummy.data?.size).thenReturn(10)
        val tvShowsMock = MutableLiveData<ResourceValue<PagedList<TvShowEntity>>>()
        tvShowsMock.value = tvShowsDataDummy

        `when`(tvShowRepository.getTvShowsData()).thenReturn(tvShowsMock)
        val tvShowData = viewModel.getTvShows().value?.data
        verify(tvShowRepository).getTvShowsData()
        assertNotNull(tvShowData)
        assertEquals(10, tvShowData?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(tvShowsDataDummy)
    }
}