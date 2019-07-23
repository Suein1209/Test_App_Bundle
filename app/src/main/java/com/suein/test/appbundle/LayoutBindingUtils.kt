@file:Suppress("unused")

package com.suein.test.appbundle

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object LayoutBindingUtils {

    @JvmStatic
    @BindingAdapter("srcPhoto")
    fun setImageResource(imageView: ImageView, vo: Photo?) {
        if (vo != null) {
            imageView.visibility = View.VISIBLE
            Glide.with(imageView.context)
                    .load("https://unsplash.it/300/150?image=${vo.id}")
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.load_placeholder)
                    .error(R.drawable.error_placeholder)
                    .into(imageView)
        } else {
            imageView.visibility = View.GONE
        }
    }
}