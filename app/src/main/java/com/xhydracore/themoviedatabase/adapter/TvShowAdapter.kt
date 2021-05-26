package com.xhydracore.themoviedatabase.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xhydracore.themoviedatabase.databinding.ItemDataBinding
import com.xhydracore.themoviedatabase.models.tvshow.ResultsTvShow
import com.xhydracore.themoviedatabase.ui.activities.DetailActivity
import com.xhydracore.themoviedatabase.ui.fragments.TvShowFragment
import com.xhydracore.themoviedatabase.utils.CustomOnItemClickListener
import com.xhydracore.themoviedatabase.utils.setAnimationRecylerView
import com.xhydracore.themoviedatabase.utils.setRoundedGlide

class TvShowAdapter(private var listItems: List<ResultsTvShow>) :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    inner class TvShowViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShows: ResultsTvShow) {
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
                                    putExtra(DetailActivity.EXTRA_DETAIL_ID, tvShows.id)
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
        val tvShow = listItems[position]
        holder.bind(tvShow)
        holder.itemView.setAnimationRecylerView()
    }

    override fun getItemCount(): Int = listItems.size
}