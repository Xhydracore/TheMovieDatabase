package com.xhydracore.themoviedatabase.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xhydracore.themoviedatabase.models.tvshow.ResponseDetailTvShow
import com.xhydracore.themoviedatabase.models.tvshow.ResultsTvShow
import com.xhydracore.themoviedatabase.repositories.remote.GetDetailTvShowCallback
import com.xhydracore.themoviedatabase.repositories.remote.GetTvShowsCallback
import com.xhydracore.themoviedatabase.repositories.remote.RemoteRepositories

class FakeTvShowRepository(private val remoteRepositories: RemoteRepositories) {

    fun getTvShowsData(): LiveData<List<ResultsTvShow>> {
        val tvShowsData = MutableLiveData<List<ResultsTvShow>>()
        remoteRepositories.getPopularTvShows(object : GetTvShowsCallback {
            override fun onResponse(responseTvShows: List<ResultsTvShow>) {
                tvShowsData.postValue(responseTvShows)
            }
        })
        return tvShowsData
    }

    fun getTvShowDetailData(tvShowId: Int): LiveData<ResponseDetailTvShow> {
        val tvShowDetailData = MutableLiveData<ResponseDetailTvShow>()
        remoteRepositories.getDetailTvShow(tvShowId, object : GetDetailTvShowCallback {
            override fun onResponse(responseDetailTvShow: ResponseDetailTvShow) {
                tvShowDetailData.postValue(responseDetailTvShow)
            }
        })
        return tvShowDetailData
    }
}