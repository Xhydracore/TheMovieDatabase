package com.xhydracore.themoviedatabase.ui.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.xhydracore.themoviedatabase.R
import com.xhydracore.themoviedatabase.databinding.FragmentTvshowBinding
import com.xhydracore.themoviedatabase.di.Injection
import com.xhydracore.themoviedatabase.ui.adapter.TvShowAdapter
import com.xhydracore.themoviedatabase.ui.viewmodels.TvShowViewModel
import com.xhydracore.themoviedatabase.utils.DefaultItemDecorator
import com.xhydracore.themoviedatabase.utils.ViewModelFactoryTvShows
import com.xhydracore.themoviedatabase.vo.Status

class TvShowFragment : Fragment(R.layout.fragment_tvshow) {

    private val binding: FragmentTvshowBinding by viewBinding()
    private val viewModel: TvShowViewModel by activityViewModels {
        ViewModelFactoryTvShows(Injection.tvShowInjectRepository(requireContext()))
    }
    private val tvShowAdapter by lazy {
        TvShowAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            with(rvTvShow) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = tvShowAdapter
                setHasFixedSize(true)
                addItemDecoration(
                    DefaultItemDecorator(
                        resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                        resources.getDimensionPixelSize(R.dimen.activity_vertical_margin)
                    )
                )
            }

            viewModel.getTvShows().observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> {
                        setProgressVisibility(false)
                        tvShowAdapter.submitList(it.data)
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
            binding.lottieTvShowSpinner.visibility = View.VISIBLE
            binding.lottieTvShowSpinner.playAnimation()
        } else {
            binding.lottieTvShowSpinner.visibility = View.GONE
            binding.lottieTvShowSpinner.pauseAnimation()
        }
    }
}