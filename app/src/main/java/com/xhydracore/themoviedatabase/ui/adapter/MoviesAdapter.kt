package com.xhydracore.themoviedatabase.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xhydracore.themoviedatabase.data.local.entities.MovieEntity
import com.xhydracore.themoviedatabase.databinding.ItemDataBinding
import com.xhydracore.themoviedatabase.ui.activities.DetailActivity
import com.xhydracore.themoviedatabase.ui.fragments.MovieFragment
import com.xhydracore.themoviedatabase.utils.CustomOnItemClickListener
import com.xhydracore.themoviedatabase.utils.setAnimationRecylerView
import com.xhydracore.themoviedatabase.utils.setRoundedGlide

class MoviesAdapter : PagedListAdapter<MovieEntity, MoviesAdapter.MovieViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK by lazy {
            object : DiffUtil.ItemCallback<MovieEntity>() {
                override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                    return oldItem.movieId == newItem.movieId
                }

                override fun areContentsTheSame(
                    oldItem: MovieEntity,
                    newItem: MovieEntity
                ): Boolean {
                    return oldItem.movieId == newItem.movieId
                }

            }
        }
    }

    inner class MovieViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MovieEntity) {
            with(binding) {
                ivPoster.setRoundedGlide(movies.posterPath)
                tvTitle.text = movies.title
                tvReleaseDate.text = movies.releaseDate
                circularRating.setProgress(movies.voteAverage, 10.0)
                tvOverview.text = movies.overview
            }
            itemView.setOnClickListener(
                CustomOnItemClickListener(
                    absoluteAdapterPosition,
                    object : CustomOnItemClickListener.OnItemClickCallback {
                        override fun onItemClicked(view: View, position: Int) {
                            Intent(itemView.context, DetailActivity::class.java)
                                .apply {
                                    putExtra(DetailActivity.EXTRA_MOVIE, movies)
                                    putExtra(
                                        DetailActivity.EXTRA_TYPE,
                                        MovieFragment::class.java.simpleName
                                    )
                                    itemView.context.startActivity(this)
                                }
                        }

                    })
            )
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val itemDataBinding =
            ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemDataBinding)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        val movie = getItem(position) as MovieEntity
        holder.bind(movie)
        holder.itemView.setAnimationRecylerView()
    }
}