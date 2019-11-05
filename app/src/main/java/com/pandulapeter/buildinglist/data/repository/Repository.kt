package com.pandulapeter.buildinglist.data.repository

import com.pandulapeter.buildinglist.R
import com.pandulapeter.buildinglist.data.model.Building
import com.pandulapeter.buildinglist.data.model.Country

class Repository {

    val buildings = listOf(
        Building(
            name = "Burj Khalifa",
            height = 828,
            constructionYear = 2010,
            country = Country.UNITED_ARAB_EMIRATES,
            thumbnailDrawableResourceId = R.drawable.img_burj_khalifa
        ),
        Building(
            name = "Shanghai Tower",
            height = 632,
            constructionYear = 2015,
            country = Country.CHINA,
            thumbnailDrawableResourceId = R.drawable.img_shanghai_tower
        ),
        Building(
            name = "Abraj Al-Bait Clock Tower",
            height = 601,
            constructionYear = 2012,
            country = Country.SAUDI_ARABIA,
            thumbnailDrawableResourceId = R.drawable.img_abraj_al_bait_clock_tower
        ),
        Building(
            name = "Ping An Finance Center",
            height = 599,
            constructionYear = 2017,
            country = Country.CHINA,
            thumbnailDrawableResourceId = R.drawable.img_ping_an_finance_center
        ),
        Building(
            name = "Goldin Finance 117",
            height = 596,
            constructionYear = 2019,
            country = Country.CHINA,
            thumbnailDrawableResourceId = R.drawable.img_goldin_finance
        ),
        Building(
            name = "Lotte World Tower",
            height = 554,
            constructionYear = 2016,
            country = Country.SOUTH_KOREA,
            thumbnailDrawableResourceId = R.drawable.img_lotte_world_tower
        ),
        Building(
            name = "One World Trade Center",
            height = 541,
            constructionYear = 2014,
            country = Country.UNITED_STATES_OF_AMERICA,
            thumbnailDrawableResourceId = R.drawable.img_one_world_trade_center
        ),
        Building(
            name = "Guangzhou CTF Finance Center",
            height = 530,
            constructionYear = 2016,
            country = Country.CHINA,
            thumbnailDrawableResourceId = R.drawable.img_guangzhou_ctf_finance_center
        ),
        Building(
            name = "Tianjin CTF Finance Center",
            height = 530,
            constructionYear = 2018,
            country = Country.CHINA,
            thumbnailDrawableResourceId = R.drawable.img_tianjin_ctf_finance_centre
        ),
        Building(
            name = "China Zun",
            height = 528,
            constructionYear = 2018,
            country = Country.CHINA,
            thumbnailDrawableResourceId = R.drawable.img_china_zun
        ),
        Building(
            name = "Taipei 101",
            height = 508,
            constructionYear = 2004,
            country = Country.TAIWAN,
            thumbnailDrawableResourceId = R.drawable.img_taipei_101
        ),
        Building(
            name = "Shanghai World Financial Center",
            height = 492,
            constructionYear = 2008,
            country = Country.CHINA,
            thumbnailDrawableResourceId = R.drawable.img_shanghai_world_financial_center
        ),
        Building(
            name = "International Commerce Centre",
            height = 484,
            constructionYear = 2010,
            country = Country.CHINA,
            thumbnailDrawableResourceId = R.drawable.img_international_commerce_centre
        ),
        Building(
            name = "Wuhan Greenland Center",
            height = 475,
            constructionYear = 2019,
            country = Country.CHINA,
            thumbnailDrawableResourceId = R.drawable.img_wuhan_greenland_center
        ),
        Building(
            name = "Central Park Tower",
            height = 472,
            constructionYear = 2020,
            country = Country.UNITED_STATES_OF_AMERICA,
            thumbnailDrawableResourceId = R.drawable.img_central_park_tower
        ),
        Building(
            name = "Lakhta Center",
            height = 462,
            constructionYear = 2018,
            country = Country.RUSSIA,
            thumbnailDrawableResourceId = R.drawable.img_lakhta_center
        ),
        Building(
            name = "Landmark 81",
            height = 461,
            constructionYear = 2018,
            country = Country.VIETNAM,
            thumbnailDrawableResourceId = R.drawable.img_landmark_81
        ),
        Building(
            name = "Changsha IFS Tower T1",
            height = 452,
            constructionYear = 2017,
            country = Country.CHINA,
            thumbnailDrawableResourceId = R.drawable.img_changsha_ifs_tower_t1
        ),
        Building(
            name = "Petronas Towers",
            height = 451,
            constructionYear = 1998,
            country = Country.MALAYSIA,
            thumbnailDrawableResourceId = R.drawable.img_petronas_towers
        ),
        Building(
            name = "Zifeng Tower",
            height = 450,
            constructionYear = 2010,
            country = Country.CHINA,
            thumbnailDrawableResourceId = R.drawable.img_zifeng_tower
        )
    )
}