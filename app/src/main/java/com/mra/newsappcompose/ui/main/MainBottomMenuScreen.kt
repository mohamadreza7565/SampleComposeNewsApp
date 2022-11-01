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
        route = ScreenConst.Main.Home,
        icon = R.drawable.ic_home_fill,
        title = "News"
    )

    object Search : MainBottomMenuScreen(
        route = ScreenConst.Main.SEARCH,
        icon = R.drawable.ic_search_fill,
        title = "Search"
    )

    object Sources : MainBottomMenuScreen(
        route = ScreenConst.Main.PROFILE,
        icon = R.drawable.ic_profile_fill,
        title = "Profile"
    )
}
