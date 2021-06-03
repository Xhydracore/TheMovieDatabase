package com.xhydracore.themoviedatabase.ui.fragments

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.xhydracore.themoviedatabase.R
import com.xhydracore.themoviedatabase.databinding.FragmentTvShowBookmarkBinding
import com.xhydracore.themoviedatabase.di.Injection
import com.xhydracore.themoviedatabase.ui.adapter.TvShowAdapter
import com.xhydracore.themoviedatabase.ui.viewmodels.BookmarkViewModel
import com.xhydracore.themoviedatabase.utils.DefaultItemDecorator
import com.xhydracore.themoviedatabase.utils.ViewModelFactoryBookmark

class TvShowBookmarkFragment : Fragment(R.layout.fragment_tv_show_bookmark) {
    private val binding: FragmentTvShowBookmarkBinding by viewBinding()
    private val viewModel: BookmarkViewModel by activityViewModels {
        ViewModelFactoryBookmark(
            Injection.movieInjectRepository(requireContext()),
            Injection.tvShowInjectRepository(requireContext())
        )
    }
    private val adapter by lazy { TvShowAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            lottieTvShowFav.visibility = View.VISIBLE
            lottieTvShowFav.playAnimation()
            rvTvShowFavorite.adapter = adapter
            rvTvShowFavorite.layoutManager = LinearLayoutManager(requireContext())
            with(rvTvShowFavorite) {
                setHasFixedSize(true)
                addItemDecoration(
                    DefaultItemDecorator(
                        resources.getDimensionPixelSize(R.dimen.activity_horizontal_margin),
                        resources.getDimensionPixelSize(R.dimen.activity_vertical_margin)
                    )
                )
            }
            viewModel.getBookmarkTvShow().observe(viewLifecycleOwner) {
                adapter.submitList(it)
                lottieTvShowFav.visibility = View.GONE
                lottieTvShowFav.cancelAnimation()
            }
        }
    }
}
