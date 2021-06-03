package com.xhydracore.themoviedatabase.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.xhydracore.themoviedatabase.data.local.entities.TvShowEntity
import com.xhydracore.themoviedatabase.data.repositories.TvShowRepository
import com.xhydracore.themoviedatabase.vo.ResourceValue

class TvShowViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {
    internal fun getTvShows(): LiveData<ResourceValue<PagedList<TvShowEntity>>> =
        tvShowRepository.getTvShowsData()
}