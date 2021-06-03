package com.xhydracore.themoviedatabase.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xhydracore.themoviedatabase.ui.fragments.MovieBookmarkFragment
import com.xhydracore.themoviedatabase.ui.fragments.TvShowBookmarkFragment

class FavoritePagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    private val fragment: ArrayList<Fragment> =
        arrayListOf(MovieBookmarkFragment(), TvShowBookmarkFragment())

    override fun getItemCount(): Int = fragment.size

    override fun createFragment(position: Int): Fragment {
        return fragment[position]
    }

}