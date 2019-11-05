package com.pandulapeter.buildinglist.data.model

import androidx.annotation.DrawableRes

data class Country(
    val name: String,
    @DrawableRes val flagDrawable: Int
)