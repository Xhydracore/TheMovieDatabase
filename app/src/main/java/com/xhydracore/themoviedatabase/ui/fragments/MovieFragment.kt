package com.xhydracore.themoviedatabase.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xhydracore.themoviedatabase.R
import com.xhydracore.themoviedatabase.adapter.MoviesAdapter
import com.xhydracore.themoviedatabase.databinding.FragmentMovieBinding
import com.xhydracore.themoviedatabase.utils.DefaultItemDecorator
import com.xhydracore.themoviedatabase.viewmodels.MovieViewModel

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MovieViewModel::class.java]
            val movies = movieViewModel.getMovies()
            val movieAdapter = MoviesAdapter(requireContext(), movies)
            with(binding.rvMovie) {
                addItemDecoration(
                    DefaultItemDecorator(
                        resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                        resources.getDimensionPixelSize(R.dimen.activity_vertical_margin)
                    )
                )
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}