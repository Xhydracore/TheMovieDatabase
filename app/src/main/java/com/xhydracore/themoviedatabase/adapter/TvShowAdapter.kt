package com.xhydracore.themoviedatabase.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xhydracore.themoviedatabase.databinding.ItemDataBinding
import com.xhydracore.themoviedatabase.models.TvShowEntity
import com.xhydracore.themoviedatabase.ui.activities.DetailActivity
import com.xhydracore.themoviedatabase.ui.fragments.TvShowFragment
import com.xhydracore.themoviedatabase.utils.CustomOnItemClickListener
import com.xhydracore.themoviedatabase.utils.ViewUtils

class TvShowAdapter(private val context: Context, private var listItems: ArrayList<TvShowEntity>) :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    inner class TvShowViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShows: TvShowEntity) {
            with(binding) {
                ViewUtils.setGlide(context, tvShows.tvShowPosterPath, ivPoster)
                tvTitle.text = tvShows.tvShowTitle
                tvReleaseDate.text = tvShows.tvShowReleaseDate
                ratingBar.rating = tvShows.tvShowRating.toFloat()
            }
            itemView.setOnClickListener(
                CustomOnItemClickListener(
                    absoluteAdapterPosition,
                    object : CustomOnItemClickListener.OnItemClickCallback {
                        override fun onItemClicked(view: View, position: Int) {
                            Intent(itemView.context, DetailActivity::class.java)
                                .apply {
                                    putExtra(DetailActivity.EXTRA_DETAIL_ID, tvShows.tvShowId)
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
    ): TvShowAdapter.TvShowViewHolder {
        val itemDataBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemDataBinding)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.TvShowViewHolder, position: Int) {
        val tvShow = listItems[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listItems.size
}