package com.xhydracore.themoviedatabase.ui.activities

import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.xhydracore.themoviedatabase.R
import com.xhydracore.themoviedatabase.databinding.ActivityBookmarkBinding
import com.xhydracore.themoviedatabase.ui.adapter.FavoritePagerAdapter

class BookmarkActivity : AppCompatActivity() {

    private val binding: ActivityBookmarkBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnBackBookmark.setOnClickListener { finish() }
        initView()
    }

    private fun initView() {
        binding.viewPagerBookmark.adapter = FavoritePagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPagerBookmark.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val tabTitle = resources.getStringArray(R.array.main_tab_title)
        TabLayoutMediator(binding.tabsLayoutBookmark, binding.viewPagerBookmark) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }
}