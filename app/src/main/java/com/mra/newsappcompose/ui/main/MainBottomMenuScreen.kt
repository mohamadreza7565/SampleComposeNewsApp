package com.mra.newsappcompose.ui.main

import com.mra.newsappcompose.R
import com.mra.newsappcompose.global.objects.ScreenConst

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
sealed class MainBottomMenuScreen(
    val route: String,
    val icon: Int,
    val title: String
) {

    object NewsList : MainBottomMenuScreen(
        route = ScreenConst.Home.NEWS_LIST,
        icon = R.drawable.ic_home_fill,
        title = "News"
    )

    object Search : MainBottomMenuScreen(
        route = ScreenConst.Home.SEARCH,
        icon = R.drawable.ic_search_fill,
        title = "Search"
    )

    object Sources : MainBottomMenuScreen(
        route = ScreenConst.Home.PROFILE,
        icon = R.drawable.ic_profile_fill,
        title = "Profile"
    )
}
