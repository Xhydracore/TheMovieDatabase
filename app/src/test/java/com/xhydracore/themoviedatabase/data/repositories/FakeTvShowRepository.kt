package com.xhydracore.themoviedatabase.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.xhydracore.themoviedatabase.data.local.LocalDataSource
import com.xhydracore.themoviedatabase.data.local.entities.GenreEntity
import com.xhydracore.themoviedatabase.data.local.entities.TvShowEntity
import com.xhydracore.themoviedatabase.data.remote.ApiResponse
import com.xhydracore.themoviedatabase.data.remote.RemoteDataSource
import com.xhydracore.themoviedatabase.data.remote.models.GenreResponse
import com.xhydracore.themoviedatabase.data.remote.models.tvshow.ResponseItemTvShows
import com.xhydracore.themoviedatabase.utils.AppExecutors
import com.xhydracore.themoviedatabase.utils.Constant
import com.xhydracore.themoviedatabase.vo.ResourceValue
import java.util.*

class FakeTvShowRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : TvShowDataSourceContract {

    private val config by lazy {
        PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()
    }

    override fun getTvShowsData(): LiveData<ResourceValue<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, ResponseItemTvShows>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                return LivePagedListBuilder(localDataSource.getTvShowFromDb(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean = data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<ResponseItemTvShows>> {
                return remoteDataSource.getPopularTvShow()
            }

            override fun saveCallResult(data: ResponseItemTvShows) {
                localDataSource.insertTvShowToDB(data.results)
            }

        }.asLiveData()
    }

    override fun checkBookmarkTvShow(tvShowId: Int): LiveData<Boolean> =
        localDataSource.checkTvShowById(tvShowId)

    override fun setBookmarkTvShow(tvShowEntity: TvShowEntity, status: Boolean) =
        appExecutors.diskIO().execute { localDataSource.updateTvShowFromDB(tvShowEntity, status) }

    override fun getBookmarkDataTvShow(): LiveData<PagedList<TvShowEntity>> {
        return LivePagedListBuilder(localDataSource.getBookmarkTvShowData(), config).build()
    }

    override fun getGenreTvShow(): LiveData<ResourceValue<List<GenreEntity>>> {
        return object :
            NetworkBoundResource<List<GenreEntity>, GenreResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<List<GenreEntity>> =
                localDataSource.getGenreTvShow()

            override fun shouldFetch(data: List<GenreEntity>?): Boolean = data.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<GenreResponse>> =
                remoteDataSource.getGenreTvShow()

            override fun saveCallResult(data: GenreResponse) {
                val tvGenres = ArrayList<GenreEntity>()
                for (genre in data.genres) {
                    genre.type = Constant.TYPE_TV
                    tvGenres.add(genre)
                }
                localDataSource.insertGenreTvShow(tvGenres)
            }
        }.asLiveData()
    }
}