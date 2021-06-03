package com.xhydracore.themoviedatabase.di

import android.content.Context
import com.xhydracore.themoviedatabase.data.local.LocalDataSource
import com.xhydracore.themoviedatabase.data.local.room.BookmarkRoomDatabase
import com.xhydracore.themoviedatabase.data.remote.RemoteDataSource
import com.xhydracore.themoviedatabase.data.remote.api.ApiConfig
import com.xhydracore.themoviedatabase.data.repositories.MoviesRepository
import com.xhydracore.themoviedatabase.data.repositories.TvShowRepository
import com.xhydracore.themoviedatabase.utils.AppExecutors

object Injection {
    fun movieInjectRepository(context: Context): MoviesRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig)
        val localDataSource =
            LocalDataSource.getInstance(BookmarkRoomDatabase.getInstance(context).bookmarkDao())
        return MoviesRepository.getInstance(remoteDataSource, localDataSource, AppExecutors())
    }

    fun tvShowInjectRepository(context: Context): TvShowRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig)
        val localDataSource =
            LocalDataSource.getInstance(BookmarkRoomDatabase.getInstance(context).bookmarkDao())
        return TvShowRepository.getInstance(remoteDataSource, localDataSource, AppExecutors())
    }
}