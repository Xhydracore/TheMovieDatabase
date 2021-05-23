package com.xhydracore.themoviedatabase.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

object ViewUtils {
    internal fun setGlide(context: Context, urlPath: String, imageView: ImageView){
        Glide.with(context).load(urlPath)
            .apply(RequestOptions().override(90,140))
            .apply(RequestOptions().transform(RoundedCorners(15))).into(imageView)
    }
}