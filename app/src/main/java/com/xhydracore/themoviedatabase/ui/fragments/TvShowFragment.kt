package com.xhydracore.themoviedatabase.ui.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.xhydracore.themoviedatabase.R
import com.xhydracore.themoviedatabase.adapter.TvShowAdapter
import com.xhydracore.themoviedatabase.databinding.FragmentTvshowBinding
import com.xhydracore.themoviedatabase.di.Injection
import com.xhydracore.themoviedatabase.utils.DefaultItemDecorator
import com.xhydracore.themoviedatabase.utils.ViewModelFactoryTvShows
import com.xhydracore.themoviedatabase.viewmodels.TvShowViewModel

class TvShowFragment : Fragment(R.layout.fragment_tvshow) {

    private val binding: FragmentTvshowBinding by viewBinding()
    private val viewModel: TvShowViewModel by activityViewModels {
        ViewModelFactoryTvShows(Injection.tvShowInjectRepository())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgressVisibility(true)
        viewModel.getTvShows().observe(requireActivity()) {
            with(binding.rvTvShow) {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = TvShowAdapter(it)
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
            binding.lottieTvShowSpinner.visibility = View.VISIBLE
            binding.lottieTvShowSpinner.playAnimation()
        } else {
            binding.lottieTvShowSpinner.visibility = View.GONE
            binding.lottieTvShowSpinner.pauseAnimation()
        }
    }
}