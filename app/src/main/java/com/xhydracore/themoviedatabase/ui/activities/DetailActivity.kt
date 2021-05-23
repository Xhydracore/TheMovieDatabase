package com.xhydracore.themoviedatabase.ui.activities

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.xhydracore.themoviedatabase.R
import com.xhydracore.themoviedatabase.databinding.ActivityDetailBinding
import com.xhydracore.themoviedatabase.models.MoviesEntity
import com.xhydracore.themoviedatabase.models.TvShowEntity
import com.xhydracore.themoviedatabase.ui.fragments.MovieFragment
import com.xhydracore.themoviedatabase.viewmodels.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private val detailContentBinding: ActivityDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels()

    companion object {
        const val EXTRA_DETAIL_ID = "extra_detail_id"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailContentBinding.btnBackDetail.setOnClickListener { finish() }
        viewModel.id = intent?.getIntExtra(EXTRA_DETAIL_ID, 0) as Int
        intent?.getStringExtra(EXTRA_TYPE).run {
            if (this.equals(MovieFragment::class.java.simpleName)) {
                viewModel.getMovieDetail()?.let { populateMovieView(it) }
            } else {
                viewModel.getTvShowDetail()?.let { populateTvShow(it) }
            }
        }
    }

    private fun populateTvShow(tvShowEntity: TvShowEntity) {
        with(detailContentBinding) {
            Glide.with(this@DetailActivity).load(tvShowEntity.tvShowPosterPath)
                .apply(
                    RequestOptions().transform(RoundedCorners(15))
                )
                .into(ivPosterDetail)
            tvOverview.text = tvShowEntity.tvShowOverview
            titleTemplate.tvTitleDetail.text = tvShowEntity.tvShowTitle
            titleTemplate.tvReleaseDate.text = tvShowEntity.tvShowReleaseDate
            titleTemplate.tvGenre.text = tvShowEntity.tvShowGenres.joinToString(" | ")
            titleTemplate.ratingBarDetail.rating = tvShowEntity.tvShowRating.toFloat()
            fabBookmark.setOnClickListener {
                showToast()
            }
        }
    }

    private fun showToast() {
        Toast.makeText(this, getString(R.string.upcoming_feature), Toast.LENGTH_SHORT).show()
    }

    private fun populateMovieView(moviesEntity: MoviesEntity) {
        with(detailContentBinding) {
            Glide.with(this@DetailActivity).load(moviesEntity.moviePosterPath)
                .apply(
                    RequestOptions().transform(RoundedCorners(15))
                )
                .into(ivPosterDetail)
            tvOverview.text = moviesEntity.movieOverview
            titleTemplate.tvTitleDetail.text = moviesEntity.movieTitle
            titleTemplate.tvReleaseDate.text = moviesEntity.movieReleaseDate
            titleTemplate.tvGenre.text = moviesEntity.movieGenres.joinToString(" | ")
            titleTemplate.ratingBarDetail.rating = moviesEntity.movieRating.toFloat()
            fabBookmark.setOnClickListener {
                showToast()
            }
        }
    }
}