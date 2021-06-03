package com.xhydracore.themoviedatabase.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xhydracore.themoviedatabase.data.local.entities.TvShowEntity
import com.xhydracore.themoviedatabase.databinding.ItemDataBinding
import com.xhydracore.themoviedatabase.ui.activities.DetailActivity
import com.xhydracore.themoviedatabase.ui.fragments.TvShowFragment
import com.xhydracore.themoviedatabase.utils.CustomOnItemClickListener
import com.xhydracore.themoviedatabase.utils.setAnimationRecylerView
import com.xhydracore.themoviedatabase.utils.setRoundedGlide

class TvShowAdapter :
    PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK by lazy {
            object : DiffUtil.ItemCallback<TvShowEntity>() {
                override fun areItemsTheSame(
                    oldItem: TvShowEntity,
                    newItem: TvShowEntity
                ): Boolean {
                    return oldItem.tvShowId == newItem.tvShowId
                }

                override fun areContentsTheSame(
                    oldItem: TvShowEntity,
                    newItem: TvShowEntity
                ): Boolean {
                    return oldItem.tvShowId == newItem.tvShowId
                }

            }
        }
    }

    inner class TvShowViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShows: TvShowEntity) {
            with(binding) {
                ivPoster.setRoundedGlide(tvShows.posterPath)
                tvTitle.text = tvShows.name
                tvReleaseDate.text = tvShows.firstAirDate
                tvOverview.text = tvShows.overview
                circularRating.setProgress(tvShows.voteAverage, 10.0)
            }
            itemView.setOnClickListener(
                CustomOnItemClickListener(
                    absoluteAdapterPosition,
                    object : CustomOnItemClickListener.OnItemClickCallback {
                        override fun onItemClicked(view: View, position: Int) {
                            Intent(itemView.context, DetailActivity::class.java)
                                .apply {
                                    putExtra(DetailActivity.EXTRA_TV_SHOW, tvShows)
                                    putExtra(
                                        DetailActivity.EXTRA_TYPE,
                                        TvShowFragment::class.java.simpleName
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
    ): TvShowViewHolder {
        val itemDataBinding =
            ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemDataBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position) as TvShowEntity
        holder.bind(tvShow)
        holder.itemView.setAnimationRecylerView()
    }
}