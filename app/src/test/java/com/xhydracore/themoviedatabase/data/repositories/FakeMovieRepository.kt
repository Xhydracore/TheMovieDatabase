package com.xhydracore.themoviedatabase.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.xhydracore.themoviedatabase.data.local.LocalDataSource
import com.xhydracore.themoviedatabase.data.local.entities.GenreEntity
import com.xhydracore.themoviedatabase.data.local.entities.MovieEntity
import com.xhydracore.themoviedatabase.data.remote.ApiResponse
import com.xhydracore.themoviedatabase.data.remote.RemoteDataSource
import com.xhydracore.themoviedatabase.data.remote.models.GenreResponse
import com.xhydracore.themoviedatabase.data.remote.models.movie.ResponseItemMovies
import com.xhydracore.themoviedatabase.utils.AppExecutors
import com.xhydracore.themoviedatabase.utils.Constant
import com.xhydracore.themoviedatabase.vo.ResourceValue

class FakeMovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSourceContract {

    private val config by lazy {
        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
    }

    override fun getMoviesData(): LiveData<ResourceValue<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, ResponseItemMovies>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                return LivePagedListBuilder(localDataSource.getMovieFromDB(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean = data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<ResponseItemMovies>> {
                return remoteDataSource.getPopularMovies()
            }

            override fun saveCallResult(data: ResponseItemMovies) {
                localDataSource.insertMovieToDB(data.results)
            }

        }.asLiveData()
    }

    override fun checkBookmarkMovie(movieId: Int): LiveData<Boolean> {
        return localDataSource.checkMovieById(movieId)
    }

    override fun setBookmarkMovie(movieEntity: MovieEntity, status: Boolean) {
        return appExecutors.diskIO()
            .execute { localDataSource.updateMovieFromDB(movieEntity, status) }
    }

    override fun getBookmarkDataMovie(): LiveData<PagedList<MovieEntity>> {
        return LivePagedListBuilder(localDataSource.getBookmarkMovieData(), config).build()
    }

    override fun getGenreMovie(): LiveData<ResourceValue<List<GenreEntity>>> {
        return object : NetworkBoundResource<List<GenreEntity>, GenreResponse>(appExecutors) {

            override fun loadFromDB(): LiveData<List<GenreEntity>> = localDataSource.getGenreMovie()

            override fun shouldFetch(data: List<GenreEntity>?): Boolean = data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<GenreResponse>> =
                remoteDataSource.getGenreMovie()

            override fun saveCallResult(data: GenreResponse) {
                val movieGenres = ArrayList<GenreEntity>()
                data.genres.forEach { genre ->
                    genre.type = Constant.TYPE_MOVIE
                    movieGenres.add(genre)
                }
                localDataSource.insertGenreMovie(movieGenres)
            }
        }.asLiveData()
    }
}