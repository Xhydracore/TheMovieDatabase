package com.xhydracore.themoviedatabase.ui.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.xhydracore.themoviedatabase.R
import com.xhydracore.themoviedatabase.databinding.FragmentMovieBinding
import com.xhydracore.themoviedatabase.di.Injection
import com.xhydracore.themoviedatabase.ui.adapter.MoviesAdapter
import com.xhydracore.themoviedatabase.ui.viewmodels.MovieViewModel
import com.xhydracore.themoviedatabase.utils.DefaultItemDecorator
import com.xhydracore.themoviedatabase.utils.ViewModelFactoryMovie
import com.xhydracore.themoviedatabase.vo.Status

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private val binding: FragmentMovieBinding by viewBinding()
    private val viewModel: MovieViewModel by activityViewModels {
        ViewModelFactoryMovie(Injection.movieInjectRepository(requireContext()))
    }
    private val movieAdapter by lazy { MoviesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            with(rvMovie) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = movieAdapter
                setHasFixedSize(true)
                addItemDecoration(
                    DefaultItemDecorator(
                        resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                        resources.getDimensionPixelSize(R.dimen.activity_vertical_margin)
                    )
                )
            }

            viewModel.getMovies().observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> {
                        setProgressVisibility(false)
                        movieAdapter.submitList(it.data)
                    }
                    Status.ERROR -> {
                        setProgressVisibility(true)
                        Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> setProgressVisibility(true)
                }
            }
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