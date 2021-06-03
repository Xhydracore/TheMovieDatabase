package com.xhydracore.themoviedatabase.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xhydracore.themoviedatabase.data.remote.api.ApiConfig
import com.xhydracore.themoviedatabase.data.remote.models.GenreResponse
import com.xhydracore.themoviedatabase.data.remote.models.movie.ResponseItemMovies
import com.xhydracore.themoviedatabase.data.remote.models.tvshow.ResponseItemTvShows
import com.xhydracore.themoviedatabase.utils.EspressoIdlingResource
import com.xhydracore.themoviedatabase.utils.SingletonHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.awaitResponse
import java.net.UnknownHostException

class RemoteDataSource(private val apiConfig: ApiConfig) {
    companion object : SingletonHolder<RemoteDataSource, ApiConfig>(::RemoteDataSource) {
        private const val TAG = "RemoteDataSource"
    }

    fun getPopularMovies(): LiveData<ApiResponse<ResponseItemMovies>> {
        EspressoIdlingResource.increment()
        val popularMovieList = MutableLiveData<ApiResponse<ResponseItemMovies>>()
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiConfig.apiService.getPopularMovie().awaitResponse()
            try {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d(TAG, it.toString())
                        popularMovieList.postValue(ApiResponse.success(it))
                    }
                } else {
                    ApiResponse.empty(response.message(), response.body())
                    Log.d(TAG, "onResponse: ${response.code()}")
                }
            } catch (e: HttpException) {
                ApiResponse.error(response.message(), response.body())
                Log.d(TAG, response.message())
            } catch (e: UnknownHostException) {
                ApiResponse.error(response.message(), response.body())
            } catch (e: Throwable) {
                ApiResponse.error(response.message(), response.body())
                Log.d(TAG, "${e.message}")
            }
            EspressoIdlingResource.decrement()
        }
        return popularMovieList
    }

    fun getPopularTvShow(): LiveData<ApiResponse<ResponseItemTvShows>> {
        EspressoIdlingResource.increment()
        val popularTvShowList = MutableLiveData<ApiResponse<ResponseItemTvShows>>()
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiConfig.apiService.getPopularTvShow().awaitResponse()
            try {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d(TAG, it.toString())
                        popularTvShowList.postValue(ApiResponse.success(it))
                    }
                } else {
                    ApiResponse.empty(response.message(), response.body())
                }
            } catch (e: HttpException) {
                ApiResponse.error(response.message(), response.body())
                Log.d(TAG, response.message())
            } catch (e: UnknownHostException) {
                ApiResponse.error(response.message(), response.body())
            } catch (e: Throwable) {
                ApiResponse.error(response.message(), response.body())
                Log.d(TAG, "${e.message}")
            }
            EspressoIdlingResource.decrement()
        }
        return popularTvShowList
    }

    fun getGenreMovie(): LiveData<ApiResponse<GenreResponse>> {
        EspressoIdlingResource.increment()
        val movieGenre = MutableLiveData<ApiResponse<GenreResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiConfig.apiService.getGenreMovie().awaitResponse()
            try {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d(TAG, it.toString())
                        movieGenre.postValue(ApiResponse.success(it))
                    }
                } else {
                    ApiResponse.empty(response.message(), response.body())
                    Log.d(TAG, "onResponse: ${response.code()}")
                }
            } catch (e: HttpException) {
                ApiResponse.error(response.message(), response.body())
                Log.d(TAG, response.message())
            } catch (e: UnknownHostException) {
                ApiResponse.error(response.message(), response.body())
            } catch (e: Throwable) {
                ApiResponse.error(response.message(), response.body())
                Log.d(TAG, "${e.message}")
            }
            EspressoIdlingResource.decrement()
        }
        return movieGenre
    }

    fun getGenreTvShow(): LiveData<ApiResponse<GenreResponse>> {
        EspressoIdlingResource.increment()
        val tvShowGenre = MutableLiveData<ApiResponse<GenreResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiConfig.apiService.getGenreTv().awaitResponse()
            try {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d(TAG, it.toString())
                        tvShowGenre.postValue(ApiResponse.success(it))
                    }
                } else {
                    ApiResponse.empty(response.message(), response.body())
                }
            } catch (e: HttpException) {
                ApiResponse.error(response.message(), response.body())
                Log.d(TAG, response.message())
            } catch (e: UnknownHostException) {
                ApiResponse.error(response.message(), response.body())
            } catch (e: Throwable) {
                ApiResponse.error(response.message(), response.body())
                Log.d(TAG, "${e.message}")
            }
            EspressoIdlingResource.decrement()
        }
        return tvShowGenre
    }
}