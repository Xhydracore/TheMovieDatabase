package com.xhydracore.themoviedatabase.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.xhydracore.themoviedatabase.R
import com.xhydracore.themoviedatabase.adapter.TvShowAdapter
import com.xhydracore.themoviedatabase.databinding.FragmentTvshowBinding
import com.xhydracore.themoviedatabase.utils.DefaultItemDecorator
import com.xhydracore.themoviedatabase.viewmodels.TvShowViewModel

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val tvShowViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvShowViewModel::class.java]
            val tvShows = tvShowViewModel.getTvShows()
            val tvShowAdapter = TvShowAdapter(requireContext(), tvShows)
            with(binding.rvTvShow) {
                addItemDecoration(
                    DefaultItemDecorator(
                        resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                        resources.getDimensionPixelSize(R.dimen.activity_vertical_margin)
                    )
                )
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}