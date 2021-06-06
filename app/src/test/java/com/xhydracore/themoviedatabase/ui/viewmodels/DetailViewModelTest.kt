package com.xhydracore.themoviedatabase.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.xhydracore.themoviedatabase.data.DataDummy
import com.xhydracore.themoviedatabase.data.local.entities.GenreEntity
import com.xhydracore.themoviedatabase.data.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.data.repositories.TvShowRepository
import com.xhydracore.themoviedatabase.vo.ResourceValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@Suppress("UNCHECKED_CAST")
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var thrown: ExpectedException = ExpectedException.none()

    @Mock
    private lateinit var moviesRepository: MoviesRepository

    @Mock
    private lateinit var tvShowRepository: TvShowRepository

    @Mock
    private lateinit var observer: Observer<ResourceValue<List<GenreEntity>>>

    @Mock
    private lateinit var stateObserver: Observer<Boolean>

    private val idTestMovie = 529203
    private val idTestTvShow = 82856

    @Before
    fun init() {
        viewModel = DetailViewModel(moviesRepository, tvShowRepository)
    }

    @Test
    fun checkBookmarkMovie() {
        val movieDataById = DataDummy.getPopularMovieDummy().find { it.movieId == idTestMovie }
        val stateOnDB = MutableLiveData<Boolean>()
        stateOnDB.value = movieDataById?.isBookmark

        `when`(moviesRepository.checkBookmarkMovie(idTestMovie)).thenReturn(stateOnDB)
        viewModel.checkBookmarkMovie(idTestMovie).observeForever(stateObserver)
        verify(stateObserver).onChanged(movieDataById?.isBookmark)
    }

    @Test
    fun checkBookmarkTvShow() {
        val tvShowDataById = DataDummy.getPopularTvShowDummy().find { it.tvShowId == idTestTvShow }
        val stateOnDB = MutableLiveData<Boolean>()
        stateOnDB.value = tvShowDataById?.isBookmark

        `when`(tvShowRepository.checkBookmarkTvShow(idTestTvShow)).thenReturn(stateOnDB)
        viewModel.checkBookmarkTvShow(idTestTvShow).observeForever(stateObserver)
        verify(stateObserver).onChanged(tvShowDataById?.isBookmark)
    }

    @Test
    fun testGetGenreMovie() {
        val dummy = ResourceValue.success(DataDummy.generateMovieGenre())
        val genres = MutableLiveData<ResourceValue<List<GenreEntity>>>()
        genres.value = dummy

        `when`(moviesRepository.getGenreMovie()).thenReturn(genres)

        viewModel.getGenreMovie().observeForever(observer)
        verify(observer).onChanged(dummy)
    }

    @Test
    fun testGetGenreTv() {
        val dummy = ResourceValue.success(DataDummy.generateTvGenre())
        val genres = MutableLiveData<ResourceValue<List<GenreEntity>>>()
        genres.value = dummy

        `when`(tvShowRepository.getGenreTvShow()).thenReturn(genres)

        viewModel.getGenreTvShow().observeForever(observer)
        verify(observer).onChanged(dummy)
    }
}