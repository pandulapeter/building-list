package com.pandulapeter.buildinglist.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

@BindingAdapter("srcCompat")
fun ImageView.setDrawable(@DrawableRes drawableResourceId: Int) {
    setImageDrawable(context.drawable(drawableResourceId))
}