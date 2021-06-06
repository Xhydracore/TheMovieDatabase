package com.xhydracore.themoviedatabase.ui.activities

import android.os.Bundle
import android.view.View
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.xhydracore.themoviedatabase.R
import com.xhydracore.themoviedatabase.data.local.entities.MovieEntity
import com.xhydracore.themoviedatabase.data.local.entities.TvShowEntity
import com.xhydracore.themoviedatabase.databinding.ActivityDetailBinding
import com.xhydracore.themoviedatabase.di.Injection.movieInjectRepository
import com.xhydracore.themoviedatabase.di.Injection.tvShowInjectRepository
import com.xhydracore.themoviedatabase.ui.fragments.MovieFragment
import com.xhydracore.themoviedatabase.ui.viewmodels.DetailViewModel
import com.xhydracore.themoviedatabase.utils.Constant.Companion.TYPE_MOVIE
import com.xhydracore.themoviedatabase.utils.Constant.Companion.TYPE_TV
import com.xhydracore.themoviedatabase.utils.ViewModelFactoryDetail
import com.xhydracore.themoviedatabase.vo.Status

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels {
        ViewModelFactoryDetail(
            movieInjectRepository(applicationContext),
            tvShowInjectRepository(applicationContext)
        )
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnBackDetail.setOnClickListener { finish() }
        binding.lottieDetail.visibility = View.VISIBLE
        binding.lottieDetail.playAnimation()

        intent?.getStringExtra(EXTRA_TYPE).run {
            if (this.equals(MovieFragment::class.java.simpleName)) {
                val movie = intent?.getParcelableExtra<MovieEntity>(EXTRA_MOVIE) as MovieEntity
                viewModel.id = movie.movieId
                populateMovieView(movie)
            } else {
                val tvShow = intent.getParcelableExtra<TvShowEntity>(EXTRA_TV_SHOW) as TvShowEntity
                viewModel.id = tvShow.tvShowId
                populateTvShowView(tvShow)
            }
        }
    }

    private fun populateMovieView(movie: MovieEntity) {
        with(binding) {
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/original${movie.posterPath}")
                .apply(
                    RequestOptions().transform(RoundedCorners(16))
                )
                .into(ivPosterDetail)
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/original${movie.backdropPath}")
                .apply(
                    RequestOptions().transform(RoundedCorners(16))
                )
                .into(ivBackdrop)
            tvOverview.text = movie.overview
            titleTemplate.tvTitleDetail.text = movie.title
            titleTemplate.tvReleaseDate.text = movie.releaseDate
            "(${movie.originalLanguage})".also { titleTemplate.tvOriginalLanguage.text = it }
            titleTemplate.ratingBarDetail.rating = movie.voteAverage.toFloat()
            lottieDetail.visibility = View.GONE
            lottieDetail.cancelAnimation()
            getGenre(TYPE_MOVIE, movie.genreIds)

            viewModel.checkBookmarkMovie(movie.movieId).observe(this@DetailActivity) {
                fabBookmark.setOnClickListener {
                    val moviesStatus = !movie.isBookmark
                    viewModel.setBookmarkMovie(movie, moviesStatus)
                    showToast(true)
                }
                setFabIcon(it)
            }
        }

    }

    private fun populateTvShowView(tvShow: TvShowEntity) {
        with(binding) {
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/original${tvShow.posterPath}")
                .apply(
                    RequestOptions().transform(RoundedCorners(16))
                )
                .into(ivPosterDetail)
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/original${tvShow.backdropPath}")
                .apply(
                    RequestOptions().transform(RoundedCorners(16))
                )
                .into(ivBackdrop)
            tvOverview.text = tvShow.overview
            titleTemplate.tvTitleDetail.text = tvShow.name
            titleTemplate.tvReleaseDate.text = tvShow.firstAirDate
            "(${tvShow.originalLanguage})".also { titleTemplate.tvOriginalLanguage.text = it }

            titleTemplate.ratingBarDetail.rating = tvShow.voteAverage.toFloat()
            lottieDetail.visibility = View.GONE
            lottieDetail.cancelAnimation()
            getGenre(TYPE_MOVIE, tvShow.genreIds)

            viewModel.checkBookmarkTvShow(tvShow.tvShowId).observe(this@DetailActivity) {
                fabBookmark.setOnClickListener {
                    val moviesStatus = !tvShow.isBookmark
                    viewModel.setBookmarkTvShow(tvShow, moviesStatus)
                    showToast(true)
                }
                setFabIcon(it)
            }
        }
    }

    private fun showToast(status: Boolean) {
        if (status) {
            Toast.makeText(this, "Added To Bookmark", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Removed from Bookmark", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFabIcon(status: Boolean) {
        with(binding) {
            if (status) fabBookmark.setImageResource(R.drawable.ic_baseline_bookmark_filled)
            else fabBookmark.setImageResource(R.drawable.ic_outline_bookmark_border_24)
        }
    }

    private fun getGenre(type: String, genreIds: List<Int>) {
        when (type) {
            TYPE_MOVIE -> {
                viewModel.getGenreMovie().observe(this, { genres ->
                    val genre = ArrayList<String>()

                    genres?.let {
                        when (genres.status) {
                            Status.LOADING -> {
                                binding.lottieDetail.visibility = View.VISIBLE
                                binding.lottieDetail.playAnimation()
                            }
                            Status.SUCCESS -> {
                                binding.lottieDetail.visibility = View.GONE
                                binding.lottieDetail.cancelAnimation()
                                genres.data?.map {
                                    if (it.genreId in genreIds) {
                                        genre.add(it.name)
                                    }
                                }
                            }
                            Status.ERROR -> {
                                binding.lottieDetail.visibility = View.GONE
                                binding.lottieDetail.cancelAnimation()
                            }
                        }
                    }
                    binding.titleTemplate.tvGenre.text = genre.joinToString(" | ")
                })
            }

            TYPE_TV -> {
                viewModel.getGenreTvShow().observe(this, { genres ->
                    val genre = ArrayList<String>()

                    genres?.let {
                        when (genres.status) {
                            Status.LOADING -> {
                                binding.lottieDetail.visibility = View.VISIBLE
                                binding.lottieDetail.playAnimation()
                            }
                            Status.SUCCESS -> {
                                binding.lottieDetail.visibility = View.GONE
                                binding.lottieDetail.cancelAnimation()
                                genres.data?.map {
                                    if (it.genreId in genreIds) {
                                        genre.add(it.name)
                                    }
                                }
                            }
                            Status.ERROR -> {
                                binding.lottieDetail.visibility = View.GONE
                                binding.lottieDetail.cancelAnimation()
                            }
                        }
                    }
                    binding.titleTemplate.tvGenre.text = genre.joinToString(" | ")
                })
            }
            else -> binding.titleTemplate.tvGenre.text = getString(R.string.unspecified_genre)
        }
    }
}