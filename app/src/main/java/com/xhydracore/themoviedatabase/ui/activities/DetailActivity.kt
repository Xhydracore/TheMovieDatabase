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
import com.xhydracore.themoviedatabase.di.Injection.movieInjectRepository
import com.xhydracore.themoviedatabase.di.Injection.tvShowInjectRepository
import com.xhydracore.themoviedatabase.ui.fragments.MovieFragment
import com.xhydracore.themoviedatabase.utils.ViewModelFactoryDetail
import com.xhydracore.themoviedatabase.viewmodels.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel: DetailViewModel by viewModels {
        ViewModelFactoryDetail(movieInjectRepository(), tvShowInjectRepository())
    }

    companion object {
        const val EXTRA_DETAIL_ID = "extra_detail_id"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnBackDetail.setOnClickListener { finish() }
        //
        viewModel.id = intent?.getIntExtra(EXTRA_DETAIL_ID, 0) as Int
        intent?.getStringExtra(EXTRA_TYPE).run {
            if (this.equals(MovieFragment::class.java.simpleName)) {
                populateMovieView()
            } else {
                populateTvShow()
            }
        }
    }

    private fun populateTvShow() {
        viewModel.getTvShowDetail().observe(this) {
            with(binding) {
                Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                    .apply(
                        RequestOptions().transform(RoundedCorners(15))
                    )
                    .into(ivPosterDetail)
                tvOverview.text = it.overview
                titleTemplate.tvTitleDetail.text = it.name
                titleTemplate.tvReleaseDate.text = it.firstAirDate
                titleTemplate.tvTagline.text = it.tagline
                titleTemplate.ratingBarDetail.rating = it.voteAverage.toFloat()
                fabBookmark.setOnClickListener {
                    showToast()
                }
            }
        }
    }

    private fun showToast() {
        Toast.makeText(this, getString(R.string.upcoming_feature), Toast.LENGTH_SHORT).show()
    }

    private fun populateMovieView() {
        viewModel.getMovieDetail().observe(this) {
            with(binding) {
                Glide.with(this@DetailActivity)
                    .load("https://image.tmdb.org/t/p/w500${it.posterPath}")
                    .apply(
                        RequestOptions().transform(RoundedCorners(15))
                    )
                    .into(ivPosterDetail)
                tvOverview.text = it.overview
                titleTemplate.tvTitleDetail.text = it.title
                titleTemplate.tvReleaseDate.text = it.releaseDate
                titleTemplate.tvTagline.text = it.tagline
                titleTemplate.ratingBarDetail.rating = it.voteAverage.toFloat()
                fabBookmark.setOnClickListener {
                    showToast()
                }
            }
        }
    }
}