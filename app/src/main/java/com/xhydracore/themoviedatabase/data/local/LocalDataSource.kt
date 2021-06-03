package com.xhydracore.themoviedatabase.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.xhydracore.themoviedatabase.data.local.entities.GenreEntity
import com.xhydracore.themoviedatabase.data.local.entities.MovieEntity
import com.xhydracore.themoviedatabase.data.local.entities.TvShowEntity
import com.xhydracore.themoviedatabase.data.local.room.BookmarkDao
import com.xhydracore.themoviedatabase.utils.Constant.Companion.TYPE_MOVIE
import com.xhydracore.themoviedatabase.utils.Constant.Companion.TYPE_TV
import com.xhydracore.themoviedatabase.utils.SingletonHolder
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocalDataSource(private val dao: BookmarkDao) {

    companion object : SingletonHolder<LocalDataSource, BookmarkDao>(::LocalDataSource)

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun getMovieFromDB(): DataSource.Factory<Int, MovieEntity> = dao.getMovie()

    fun getTvShowFromDb(): DataSource.Factory<Int, TvShowEntity> = dao.getTvShow()

    fun getGenreMovie(): LiveData<List<GenreEntity>> = dao.getGenre(TYPE_MOVIE)

    fun getGenreTvShow() = dao.getGenre(TYPE_TV)

    fun insertMovieToDB(movies: List<MovieEntity>) =
        executorService.execute { dao.insertMovies(movies) }

    fun insertTvShowToDB(tvShows: List<TvShowEntity>) =
        executorService.execute { dao.insertTvShow(tvShows) }

    fun insertGenreMovie(movieGenres: List<GenreEntity>) {
        dao.insertGenreMovie(movieGenres)
    }

    fun insertGenreTvShow(tvGenres: List<GenreEntity>) {
        dao.insertGenreTvShow(tvGenres)
    }

    fun updateMovieFromDB(movieEntity: MovieEntity, status: Boolean) {
        movieEntity.isBookmark = status
        dao.updateBookmarkMovie(movieEntity)
    }

    fun updateTvShowFromDB(tvShowEntity: TvShowEntity, status: Boolean) {
        tvShowEntity.isBookmark = status
        dao.updateBookmarkTvShow(tvShowEntity)
    }

    fun checkMovieById(movieId: Int): LiveData<Boolean> = dao.checkMovieBookmark(movieId)

    fun checkTvShowById(tvShowId: Int): LiveData<Boolean> = dao.checkTvShowBookmark(tvShowId)

    fun getBookmarkMovieData() = dao.getBookmarkedMovies()

    fun getBookmarkTvShowData() = dao.getBookmarkedTvShow()
}