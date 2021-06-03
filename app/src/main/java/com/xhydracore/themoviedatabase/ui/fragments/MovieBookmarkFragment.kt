package com.xhydracore.themoviedatabase.ui.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.xhydracore.themoviedatabase.R
import com.xhydracore.themoviedatabase.databinding.FragmentMovieBookmarkBinding
import com.xhydracore.themoviedatabase.di.Injection
import com.xhydracore.themoviedatabase.ui.adapter.MoviesAdapter
import com.xhydracore.themoviedatabase.ui.viewmodels.BookmarkViewModel
import com.xhydracore.themoviedatabase.utils.DefaultItemDecorator
import com.xhydracore.themoviedatabase.utils.ViewModelFactoryBookmark

class MovieBookmarkFragment : Fragment(R.layout.fragment_movie_bookmark) {
    private val binding: FragmentMovieBookmarkBinding by viewBinding()
    private val viewModel: BookmarkViewModel by activityViewModels {
        ViewModelFactoryBookmark(
            Injection.movieInjectRepository(requireContext()),
            Injection.tvShowInjectRepository(requireContext())
        )
    }
    private val adapter by lazy { MoviesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lottieMovieFav.visibility = View.VISIBLE
            lottieMovieFav.playAnimation()
            rvMovieFavorite.adapter = adapter
            rvMovieFavorite.layoutManager = LinearLayoutManager(requireContext())
            with(rvMovieFavorite) {
                setHasFixedSize(true)
                addItemDecoration(
                    DefaultItemDecorator(
                        resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                        resources.getDimensionPixelSize(R.dimen.activity_vertical_margin)
                    )
                )
            }
            viewModel.getBookmarkMovie().observe(viewLifecycleOwner) {
                adapter.submitList(it)
                lottieMovieFav.visibility = View.GONE
                lottieMovieFav.cancelAnimation()
            }
        }
    }


}
