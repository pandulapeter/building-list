package com.pandulapeter.buildinglist.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <B : ViewDataBinding> ViewGroup.inflate(@LayoutRes layoutResourceId: Int): B = DataBindingUtil.inflate(LayoutInflater.from(context), layoutResourceId, this, false)