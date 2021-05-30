package com.xhydracore.themoviedatabase.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.xhydracore.themoviedatabase.data.DataDummy
import com.xhydracore.themoviedatabase.repositories.remote.GetDetailMovieCallback
import com.xhydracore.themoviedatabase.repositories.remote.GetMoviesCallback
import com.xhydracore.themoviedatabase.repositories.remote.RemoteRepositories
import com.xhydracore.themoviedatabase.utils.LiveDataTestUtil
import com.xhydracore.themoviedatabase.utils.TestHelper.anyOfT
import com.xhydracore.themoviedatabase.utils.TestHelper.eqOfT
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.verify

class MoviesRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteRepositories = Mockito.mock(RemoteRepositories::class.java)
    private val moviesRepository = FakeMovieRepository(remoteRepositories)

    private val movieData = DataDummy.getPopularMovieDummy()
    private val idTestMovie = 399566

    @Test
    fun getMoviesData() {
        Mockito.doAnswer {
            (it.arguments[0] as GetMoviesCallback).onResponse(movieData)
        }.`when`(remoteRepositories).getPopularMovies(anyOfT(GetMoviesCallback::class.java))

        val movieResult = LiveDataTestUtil.getValue(moviesRepository.getMoviesData())
        verify(remoteRepositories).getPopularMovies(anyOfT(GetMoviesCallback::class.java))
        assertNotNull(movieResult)
        assertEquals(movieData.size, movieResult.size)
    }

    @Test
    fun getDetailMovieData() {
        Mockito.doAnswer {
            (it.arguments[1] as GetDetailMovieCallback).onResponse(
                DataDummy.getDetailMovie(
                    idTestMovie
                )
            )
        }.`when`(remoteRepositories)
            .getDetailMovie(eqOfT(idTestMovie), anyOfT(GetDetailMovieCallback::class.java))

        val movieDetailResult =
            LiveDataTestUtil.getValue(moviesRepository.getDetailMoviesData(idTestMovie))
        verify(remoteRepositories).getDetailMovie(
            eqOfT(idTestMovie),
            anyOfT(GetDetailMovieCallback::class.java)
        )
        assertNotNull(movieDetailResult)
        assertEquals(movieDetailResult, DataDummy.getDetailMovie(idTestMovie))
    }
}