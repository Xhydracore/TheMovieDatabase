package com.xhydracore.themoviedatabase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.xhydracore.themoviedatabase.models.tvshow.ResultsTvShow
import com.xhydracore.themoviedatabase.repositories.TvShowRepository

class TvShowViewModel(private val tvShowRepository: TvShowRepository) : ViewModel() {
    internal fun getTvShows(): LiveData<List<ResultsTvShow>> = tvShowRepository.getTvShowsData()
}