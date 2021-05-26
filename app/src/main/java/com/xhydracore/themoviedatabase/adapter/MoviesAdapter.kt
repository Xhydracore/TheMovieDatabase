package com.xhydracore.themoviedatabase.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xhydracore.themoviedatabase.databinding.ItemDataBinding
import com.xhydracore.themoviedatabase.models.movie.ResultsMovie
import com.xhydracore.themoviedatabase.ui.activities.DetailActivity
import com.xhydracore.themoviedatabase.ui.fragments.MovieFragment
import com.xhydracore.themoviedatabase.utils.CustomOnItemClickListener
import com.xhydracore.themoviedatabase.utils.setAnimationRecylerView
import com.xhydracore.themoviedatabase.utils.setRoundedGlide

class MoviesAdapter(
    private val listItems: List<ResultsMovie>
) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: ResultsMovie) {
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
                                    putExtra(DetailActivity.EXTRA_DETAIL_ID, movies.id)
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
        val movie = listItems[position]
        holder.bind(movie)
        holder.itemView.setAnimationRecylerView()
    }

    override fun getItemCount(): Int = listItems.size

}