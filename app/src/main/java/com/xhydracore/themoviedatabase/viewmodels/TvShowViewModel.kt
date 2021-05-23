package com.xhydracore.themoviedatabase.viewmodels

import androidx.lifecycle.ViewModel
import com.xhydracore.themoviedatabase.models.TvShowEntity
import com.xhydracore.themoviedatabase.repositories.TvShowRepository

class TvShowViewModel : ViewModel() {
    internal fun getTvShows(): ArrayList<TvShowEntity> = TvShowRepository.getTvShowData()
}