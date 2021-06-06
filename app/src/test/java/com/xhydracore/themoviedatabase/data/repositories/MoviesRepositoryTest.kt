package com.xhydracore.themoviedatabase.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource.Factory
import com.xhydracore.themoviedatabase.data.DataDummy
import com.xhydracore.themoviedatabase.data.local.LocalDataSource
import com.xhydracore.themoviedatabase.data.local.entities.GenreEntity
import com.xhydracore.themoviedatabase.data.local.entities.MovieEntity
import com.xhydracore.themoviedatabase.data.remote.RemoteDataSource
import com.xhydracore.themoviedatabase.utils.AppExecutors
import com.xhydracore.themoviedatabase.utils.LiveDataTestUtil
import com.xhydracore.themoviedatabase.utils.PagedListUtil
import com.xhydracore.themoviedatabase.vo.ResourceValue
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify

class MoviesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val moviesRepository =
        FakeMovieRepository(remoteDataSource, localDataSource, appExecutors)

    private val movieData = DataDummy.getPopularMovieDummy()
    private val movieGenreResponse = DataDummy.generateMovieGenre()
    private val idTestMovie = 529203

    @Test
    fun getMoviesData() {
        val myDataSourceFactory =
            mock(Factory::class.java) as Factory<Int, MovieEntity>
        `when`(localDataSource.getMovieFromDB()).thenReturn(myDataSourceFactory)
        moviesRepository.getMoviesData()

        val movieResult =
            ResourceValue.success(PagedListUtil.mockPagedList(DataDummy.getPopularMovieDummy()))
        verify(localDataSource).getMovieFromDB()
        assertNotNull(movieResult.data)
        assertEquals(movieData.size, movieResult.data?.size)
    }

    @Test
    fun checkBookmarkMovie() {
        val stateOnDB = MutableLiveData<Boolean>()
        stateOnDB.value = movieData[0].isBookmark
        `when`(localDataSource.checkMovieById(idTestMovie)).thenReturn(stateOnDB)

        val stateOnRepo =
            LiveDataTestUtil.getValue(moviesRepository.checkBookmarkMovie(idTestMovie))

        verify(localDataSource).checkMovieById(idTestMovie)
        assertTrue(stateOnRepo)
        assertEquals(stateOnDB.value, stateOnRepo)
    }

    @Test
    fun getBookmarkDataMovie() {
        val myDataSourceFactory = mock(Factory::class.java) as Factory<Int, MovieEntity>
        val bookmarkedMovie = DataDummy.generateBookmarkMovieData()
        `when`(localDataSource.getBookmarkMovieData()).thenReturn(myDataSourceFactory)
        moviesRepository.getBookmarkDataMovie()

        val movieResult = PagedListUtil.mockPagedList(bookmarkedMovie)
        verify(localDataSource).getBookmarkMovieData()
        assertNotNull(movieResult)
        assertEquals(2, movieResult.size)
    }

    @Test
    fun getGenreMovie() {
        val dummy = MutableLiveData<List<GenreEntity>>()
        dummy.value = DataDummy.generateMovieGenre()
        `when`(localDataSource.getGenreMovie()).thenReturn(dummy)

        val movieGenreEntities = LiveDataTestUtil.getValue(moviesRepository.getGenreMovie())

        verify(localDataSource).getGenreMovie()
        assertNotNull(movieGenreEntities.data)
        assertEquals(movieGenreResponse.size, movieGenreEntities.data?.size)
    }
}