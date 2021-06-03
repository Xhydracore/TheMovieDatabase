package com.xhydracore.themoviedatabase.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.xhydracore.themoviedatabase.data.local.entities.GenreEntity
import com.xhydracore.themoviedatabase.data.local.entities.MovieEntity
import com.xhydracore.themoviedatabase.vo.ResourceValue

interface MovieDataSourceContract {
    fun getMoviesData(): LiveData<ResourceValue<PagedList<MovieEntity>>>

    fun checkBookmarkMovie(movieId: Int): LiveData<Boolean>

    fun setBookmarkMovie(movieEntity: MovieEntity, status: Boolean)

    fun getBookmarkDataMovie(): LiveData<PagedList<MovieEntity>>

    fun getGenreMovie(): LiveData<ResourceValue<List<GenreEntity>>>
}