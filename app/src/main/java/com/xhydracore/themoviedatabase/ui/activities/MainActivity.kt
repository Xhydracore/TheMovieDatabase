package com.xhydracore.themoviedatabase.ui.activities

import android.content.Intent
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.xhydracore.themoviedatabase.R
import com.xhydracore.themoviedatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.background = null
        navView.setupWithNavController(navController)
        // fab intent BookMark Activity
        val fabBookmark: FloatingActionButton = binding.fabBookmarkActivity
        fabBookmark.setOnClickListener{
            startActivity(Intent(this, BookmarkActivity::class.java))
        }
    }




}