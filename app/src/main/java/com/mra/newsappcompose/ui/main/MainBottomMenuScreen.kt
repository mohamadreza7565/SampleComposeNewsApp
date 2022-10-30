package com.mra.newsappcompose.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Abc
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.mra.newsappcompose.global.ScreenConst

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
sealed class MainBottomMenuScreen(
    val route:String,
    val  icon:ImageVector,
    val title:String
){

    object NewsList : MainBottomMenuScreen(
        route= ScreenConst.Home.NEWS_LIST,
        icon = Icons.Outlined.Home,
        title = "News"
    )

    object Catrgories : MainBottomMenuScreen(
        route= ScreenConst.Home.CATEGORY,
        icon = Icons.Outlined.Category,
        title = "Catrgories"
    )

    object Sources : MainBottomMenuScreen(
        route= ScreenConst.Home.SOURCE,
        icon = Icons.Outlined.Abc,
        title = "Sources"
    )
}
