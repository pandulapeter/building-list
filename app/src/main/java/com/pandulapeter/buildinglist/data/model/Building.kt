package com.pandulapeter.buildinglist.data.model

import androidx.annotation.DrawableRes
import java.util.UUID

data class Building(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val height: Int,
    val constructionYear: Int,
    val country: Country,
    @DrawableRes val thumbnailDrawableResourceId: Int
)