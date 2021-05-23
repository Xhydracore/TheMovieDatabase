package com.xhydracore.themoviedatabase.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xhydracore.themoviedatabase.databinding.ItemDataBinding
import com.xhydracore.themoviedatabase.models.MoviesEntity
import com.xhydracore.themoviedatabase.ui.activities.DetailActivity
import com.xhydracore.themoviedatabase.ui.fragments.MovieFragment
import com.xhydracore.themoviedatabase.utils.CustomOnItemClickListener
import com.xhydracore.themoviedatabase.utils.ViewUtils

class MoviesAdapter(
    private val context: Context,
    private val listItems: ArrayList<MoviesEntity>
) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movies: MoviesEntity) {
            with(binding) {
                ViewUtils.setGlide(context, movies.moviePosterPath, ivPoster)
                tvTitle.text = movies.movieTitle
                tvReleaseDate.text = movies.movieReleaseDate
                ratingBar.rating = movies.movieRating.toFloat()
            }
            itemView.setOnClickListener(
                CustomOnItemClickListener(
                    absoluteAdapterPosition,
                    object : CustomOnItemClickListener.OnItemClickCallback {
                        override fun onItemClicked(view: View, position: Int) {
                            Intent(itemView.context, DetailActivity::class.java)
                                .apply {
                                    putExtra(DetailActivity.EXTRA_DETAIL_ID, movies.movieId)
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
    ): MoviesAdapter.MovieViewHolder {
        val itemDataBinding =
            ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemDataBinding)
    }

    override fun onBindViewHolder(
        holder: MoviesAdapter.MovieViewHolder,
        position: Int
    ) {
        val movie = listItems[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listItems.size

}