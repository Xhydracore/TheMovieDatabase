package com.xhydracore.themoviedatabase.di

import com.xhydracore.themoviedatabase.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.repositories.TvShowRepository

object Injection {
    fun movieInjectRepository(): MoviesRepository {
        return MoviesRepository.getInstance
    }

    fun tvShowInjectRepository(): TvShowRepository {
        return TvShowRepository.getInstance
    }
}