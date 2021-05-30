package com.xhydracore.themoviedatabase.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.xhydracore.themoviedatabase.data.DataDummy
import com.xhydracore.themoviedatabase.repositories.remote.GetDetailTvShowCallback
import com.xhydracore.themoviedatabase.repositories.remote.GetTvShowsCallback
import com.xhydracore.themoviedatabase.repositories.remote.RemoteRepositories
import com.xhydracore.themoviedatabase.utils.LiveDataTestUtil
import com.xhydracore.themoviedatabase.utils.TestHelper.anyOfT
import com.xhydracore.themoviedatabase.utils.TestHelper.eqOfT
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class TvShowRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteRepositories = Mockito.mock(RemoteRepositories::class.java)
    private val tvShowRepository = FakeTvShowRepository(remoteRepositories)

    private val tvShowData = DataDummy.getPopularTvShowDummy()
    private val idTestTvShow = 791373

    @Test
    fun getTvShowsData() {
        Mockito.doAnswer {
            (it.arguments[0] as GetTvShowsCallback).onResponse(tvShowData)
        }.`when`(remoteRepositories).getPopularTvShows(anyOfT(GetTvShowsCallback::class.java))

        val tvShowResult = LiveDataTestUtil.getValue(tvShowRepository.getTvShowsData())
        verify(remoteRepositories).getPopularTvShows(anyOfT(GetTvShowsCallback::class.java))
        Assert.assertNotNull(tvShowResult)
        assertEquals(tvShowData.size, tvShowResult.size)
        assertEquals(tvShowData[0].id, tvShowResult[0].id)
    }

    @Test
    fun getTvShowDetailData() {
        Mockito.doAnswer {
            (it.arguments[1] as GetDetailTvShowCallback)
                .onResponse(DataDummy.getDetailTvShow(idTestTvShow))
        }.`when`(remoteRepositories)
            .getDetailTvShow(eqOfT(idTestTvShow), anyOfT(GetDetailTvShowCallback::class.java))

        val tvShowDetailResult =
            LiveDataTestUtil.getValue(tvShowRepository.getTvShowDetailData(idTestTvShow))
        verify(remoteRepositories).getDetailTvShow(
            eqOfT(idTestTvShow),
            anyOfT(GetDetailTvShowCallback::class.java)
        )
        Assert.assertNotNull(tvShowDetailResult)
        assertEquals(tvShowDetailResult, DataDummy.getDetailTvShow(idTestTvShow))
        assertEquals(tvShowDetailResult.id, tvShowData[1].id)
    }
}