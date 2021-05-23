package com.xhydracore.themoviedatabase.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.xhydracore.themoviedatabase.R
import com.xhydracore.themoviedatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.background = null
        navView.setupWithNavController(navController)
        // fab intent BookMark Activity
        val fabBookmark: FloatingActionButton = binding.fabBookmarkActivity
        fabBookmark.setOnClickListener{
            Toast.makeText(this,getString(R.string.upcoming_feature), Toast.LENGTH_SHORT).show()
        }
    }




}