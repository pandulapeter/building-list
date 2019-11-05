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

    CHINA(
        nameResourceId = R.string.country_china,
        flagDrawableResourceId = R.drawable.flag_china
    ),
    KUWAIT(
        nameResourceId = R.string.country_kuwait,
        flagDrawableResourceId = R.drawable.flag_kuwait
    ),
    MALAYSIA(
        nameResourceId = R.string.country_malaysia,
        flagDrawableResourceId = R.drawable.flag_malaysia
    ),
    RUSSIA(
        nameResourceId = R.string.country_russia,
        flagDrawableResourceId = R.drawable.flag_russia
    ),
    SAUDI_ARABIA(
        nameResourceId = R.string.country_saudi_arabia,
        flagDrawableResourceId = R.drawable.flag_saudi_arabia
    ),
    SOUTH_KOREA(
        nameResourceId = R.string.country_south_korea,
        flagDrawableResourceId = R.drawable.flag_south_korea
    ),
    TAIWAN(
        nameResourceId = R.string.country_taiwan,
        flagDrawableResourceId = R.drawable.flag_taiwan
    ),
    UNITED_ARAB_EMIRATES(
        nameResourceId = R.string.country_united_arab_emirates,
        flagDrawableResourceId = R.drawable.flag_united_arab_emirates
    ),
    UNITED_STATES_OF_AMERICA(
        nameResourceId = R.string.country_united_states_of_america,
        flagDrawableResourceId = R.drawable.flag_united_states_of_america
    ),
    VIETNAM(
        nameResourceId = R.string.country_vietnam,
        flagDrawableResourceId = R.drawable.flag_vietnam
    )
}