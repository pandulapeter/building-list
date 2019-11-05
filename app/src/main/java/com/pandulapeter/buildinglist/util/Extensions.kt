package com.pandulapeter.buildinglist.util

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <B : ViewDataBinding> ViewGroup.inflate(@LayoutRes layoutResourceId: Int): B = DataBindingUtil.inflate(LayoutInflater.from(context), layoutResourceId, this, false)

fun Context.drawable(@DrawableRes drawableResourceId: Int) = AppCompatResources.getDrawable(this, drawableResourceId)