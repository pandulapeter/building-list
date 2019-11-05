package com.pandulapeter.buildinglist.data.model

import androidx.annotation.DrawableRes

data class Building(
    val name: String,
    val height: Int,
    val constructionYear: Int,
    val country: Country,
    @DrawableRes val thumbnailDrawable: Int
)