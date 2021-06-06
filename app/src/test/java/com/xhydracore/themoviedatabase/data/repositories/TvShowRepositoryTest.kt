package com.xhydracore.themoviedatabase.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource.Factory
import com.xhydracore.themoviedatabase.data.DataDummy
import com.xhydracore.themoviedatabase.data.local.LocalDataSource
import com.xhydracore.themoviedatabase.data.local.entities.GenreEntity
import com.xhydracore.themoviedatabase.data.local.entities.TvShowEntity
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

class TvShowRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val tvShowRepository =
        FakeTvShowRepository(remoteDataSource, localDataSource, appExecutors)

    private val tvShowData = DataDummy.getPopularTvShowDummy()
    private val tvShowGenreResponse = DataDummy.generateTvGenre()
    private val idTestTvShow = 82856

    @Test
    fun getTvShowsData() {
        val myDataSourceFactory =
            mock(Factory::class.java) as Factory<Int, TvShowEntity>
        `when`(localDataSource.getTvShowFromDb()).thenReturn(myDataSourceFactory)
        tvShowRepository.getTvShowsData()

        val tvShowResult =
            ResourceValue.success(PagedListUtil.mockPagedList(DataDummy.getPopularTvShowDummy()))
        verify(localDataSource).getTvShowFromDb()
        assertNotNull(tvShowResult.data)
        assertEquals(tvShowData.size, tvShowResult.data?.size)
    }

    @Test
    fun checkBookmarkTvShow() {
        val stateOnDB = MutableLiveData<Boolean>()
        stateOnDB.value = tvShowData[0].isBookmark
        `when`(localDataSource.checkTvShowById(idTestTvShow)).thenReturn(stateOnDB)

        val stateOnRepo =
            LiveDataTestUtil.getValue(tvShowRepository.checkBookmarkTvShow(idTestTvShow))

        verify(localDataSource).checkTvShowById(idTestTvShow)
        assertFalse(stateOnRepo)
        assertEquals(stateOnDB.value, stateOnRepo)
    }

    @Test
    fun getBookmarkDataTvShow() {
        val myDataSourceFactory = mock(Factory::class.java) as Factory<Int, TvShowEntity>
        val bookmarkedTvShow = DataDummy.generateBookmarkTvShowData()
        `when`(localDataSource.getBookmarkTvShowData()).thenReturn(myDataSourceFactory)
        tvShowRepository.getBookmarkDataTvShow()

        val tvShowResult = PagedListUtil.mockPagedList(bookmarkedTvShow)
        verify(localDataSource).getBookmarkTvShowData()
        assertNotNull(tvShowResult)
        assertEquals(1, tvShowResult.size)
    }

    @Test
    fun getGenreTv() {
        val dummy = MutableLiveData<List<GenreEntity>>()
        dummy.value = DataDummy.generateTvGenre()
        `when`(localDataSource.getGenreTvShow()).thenReturn(dummy)

        val tvGenreEntities = LiveDataTestUtil.getValue(tvShowRepository.getGenreTvShow())

        verify(localDataSource).getGenreTvShow()
        assertNotNull(tvGenreEntities.data)
        assertEquals(tvShowGenreResponse.size, tvGenreEntities.data?.size)
    }
}