package com.xhydracore.themoviedatabase.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.xhydracore.themoviedatabase.data.DataDummy
import com.xhydracore.themoviedatabase.models.movie.ResponseDetailMovie
import com.xhydracore.themoviedatabase.models.tvshow.ResponseDetailTvShow
import com.xhydracore.themoviedatabase.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.repositories.TvShowRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify

@Suppress("UNCHECKED_CAST")
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var thrown: ExpectedException = ExpectedException.none()

    private var moviesRepository = mock(MoviesRepository::class.java)
    private var tvShowRepository = mock(TvShowRepository::class.java)
    private var observer = mock(Observer::class.java)

    private val idTestMovie = 399566
    private val idTestTvShow = 791373

    @Before
    fun init() {
        viewModel = DetailViewModel(moviesRepository, tvShowRepository)
    }

    @Test
    fun validateDataMovieWithId() {
        val movieDataDummy = DataDummy.getDetailMovie(idTestMovie)
        val moviesLiveDataMock = MutableLiveData<ResponseDetailMovie>()
        moviesLiveDataMock.value = movieDataDummy

        `when`(moviesRepository.getDetailMoviesData(idTestMovie)).thenReturn(moviesLiveDataMock)
        viewModel.id = idTestMovie
        assertNotNull(viewModel.getMovieDetail())
        verify(moviesRepository).getDetailMoviesData(viewModel.id)
        assertEquals(moviesLiveDataMock, viewModel.getMovieDetail())

        viewModel.getMovieDetail().observeForever(observer as Observer<ResponseDetailMovie>)
        verify(observer as Observer<ResponseDetailMovie>).onChanged(movieDataDummy)
    }

    @Test
    fun validateDataTvShowWithId() {
        val tvShowDataDummy = DataDummy.getDetailTvShow(idTestTvShow)
        val tvShowLiveDataMock = MutableLiveData<ResponseDetailTvShow>()
        tvShowLiveDataMock.value = tvShowDataDummy

        `when`(tvShowRepository.getTvShowDetailData(idTestTvShow)).thenReturn(tvShowLiveDataMock)
        viewModel.id = idTestTvShow
        val getTvShowDetailData = viewModel.getTvShowDetail()
        assertNotNull(getTvShowDetailData)
        verify(tvShowRepository).getTvShowDetailData(viewModel.id)
        assertEquals(tvShowLiveDataMock, getTvShowDetailData)

        viewModel.getTvShowDetail().observeForever(observer as Observer<in ResponseDetailTvShow>)
        verify(observer as Observer<ResponseDetailTvShow>).onChanged(tvShowDataDummy)
    }

    @Test
    @Throws(NullPointerException::class)
    fun invalidIdMovie() {
        thrown.expect(java.lang.NullPointerException::class.java)
        val movieDataDummy = DataDummy.getDetailMovie(0)
        val moviesLiveDataMock = MutableLiveData<ResponseDetailMovie>()
        moviesLiveDataMock.value = movieDataDummy

        thrown.expect(NullPointerException::class.java)
        `when`(moviesRepository.getDetailMoviesData(0)).thenReturn(moviesLiveDataMock)
        viewModel.id = 0
        assertNull(viewModel.getMovieDetail())
    }

    @Test
    @Throws(NullPointerException::class)
    fun invalidIdTvShow() {
        thrown.expect(NullPointerException::class.java)
        val tvShowDataDummy = DataDummy.getDetailTvShow(0)
        val tvShowDataLiveMock = MutableLiveData<ResponseDetailTvShow>()
        tvShowDataLiveMock.value = tvShowDataDummy

        thrown.expect(NullPointerException::class.java)
        `when`(tvShowRepository.getTvShowDetailData(0)).thenReturn(tvShowDataLiveMock)
        viewModel.id = 0
        assertNull(viewModel.getTvShowDetail())
    }
}