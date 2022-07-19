package com.challenge.repos.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.challenge.repos.R

/**
 * Created by Mahmoud Hamwi on 16-Jul-22.
 */
class ImageLoaderUtil {
    companion object {
        fun loadImage(
            url: String?,
            imageView: ImageView,
        ) {
            try {
                val options: RequestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .fitCenter()

                Glide.with(imageView.context).applyDefaultRequestOptions(options)
                    .load(url)
                    .into(imageView)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}