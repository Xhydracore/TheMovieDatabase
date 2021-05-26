package com.xhydracore.themoviedatabase.ui.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.xhydracore.themoviedatabase.R
import com.xhydracore.themoviedatabase.adapter.MoviesAdapter
import com.xhydracore.themoviedatabase.databinding.FragmentMovieBinding
import com.xhydracore.themoviedatabase.di.Injection
import com.xhydracore.themoviedatabase.utils.DefaultItemDecorator
import com.xhydracore.themoviedatabase.utils.ViewModelFactoryMovie
import com.xhydracore.themoviedatabase.viewmodels.MovieViewModel

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private val binding: FragmentMovieBinding by viewBinding()
    private val viewModel: MovieViewModel by activityViewModels {
        ViewModelFactoryMovie(Injection.movieInjectRepository())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgressVisibility(true)
        viewModel.getMovies().observe(requireActivity()) {
            with(binding.rvMovie) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = MoviesAdapter(it)
                setHasFixedSize(true)
                addItemDecoration(
                    DefaultItemDecorator(
                        resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                        resources.getDimensionPixelSize(R.dimen.activity_vertical_margin)
                    )
                )
            }
            setProgressVisibility(false)
        }
    }

    private fun setProgressVisibility(state: Boolean) {
        if (state) {
            binding.lottieMovieSpinner.visibility = View.VISIBLE
            binding.lottieMovieSpinner.playAnimation()
        } else {
            binding.lottieMovieSpinner.visibility = View.GONE
            binding.lottieMovieSpinner.pauseAnimation()
        }
    }
}