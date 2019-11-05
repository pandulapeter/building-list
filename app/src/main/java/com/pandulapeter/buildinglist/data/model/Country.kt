package com.pandulapeter.buildinglist.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.pandulapeter.buildinglist.R
import java.util.UUID

enum class Country(
    val id: String = UUID.randomUUID().toString(),
    @StringRes val nameResourceId: Int,
    @DrawableRes val flagDrawableResourceId: Int
) {

    UNITED_ARAB_EMIRATES(
        nameResourceId = R.string.country_united_arab_emirates,
        flagDrawableResourceId = R.drawable.flag_united_arab_emirates
    ),
    CHINA(
        nameResourceId = R.string.country_china,
        flagDrawableResourceId = R.drawable.flag_china
    ),
    SAUDI_ARABIA(
        nameResourceId = R.string.country_saudi_arabia,
        flagDrawableResourceId = R.drawable.flag_saudi_arabia
    )
}