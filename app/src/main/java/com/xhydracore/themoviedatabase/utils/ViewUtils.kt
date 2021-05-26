package com.xhydracore.themoviedatabase.utils

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.xhydracore.themoviedatabase.R

internal fun ImageView.setRoundedGlide(urlPath: String) {
    Glide.with(context).load("https://image.tmdb.org/t/p/w500/$urlPath")
        .apply(RequestOptions().override(140, 180))
        .apply(RequestOptions().transform(RoundedCorners(8))).into(this)
}

internal fun View.setAnimationRecylerView() {
    startAnimation(AnimationUtils.loadAnimation(context, R.anim.recyclerview_anim))
}

