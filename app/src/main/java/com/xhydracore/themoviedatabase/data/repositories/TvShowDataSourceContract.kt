package com.xhydracore.themoviedatabase.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.xhydracore.themoviedatabase.data.local.entities.GenreEntity
import com.xhydracore.themoviedatabase.data.local.entities.TvShowEntity
import com.xhydracore.themoviedatabase.vo.ResourceValue

interface TvShowDataSourceContract {
    fun getTvShowsData(): LiveData<ResourceValue<PagedList<TvShowEntity>>>

    fun checkBookmarkTvShow(tvShowId: Int): LiveData<Boolean>

    fun setBookmarkTvShow(tvShowEntity: TvShowEntity, status: Boolean)

    fun getBookmarkTvShow(): LiveData<PagedList<TvShowEntity>>

    fun getGenreTvShow(): LiveData<ResourceValue<List<GenreEntity>>>
}