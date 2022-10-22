package com.mra.newsappcompose.ui.global

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Abc
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.mra.newsappcompose.ui.data.enums.Screens

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
sealed class BottomMenuScreen(
    val route:String,
    val  icon:ImageVector,
    val title:String
){

    object NewsList : BottomMenuScreen(
        route=Screens.LIST_NEWS.name,
        icon = Icons.Outlined.Home,
        title = "News"
    )

    object Catrgories : BottomMenuScreen(
        route= Screens.CATEGORIES.name,
        icon = Icons.Outlined.Category,
        title = "Catrgories"
    )

    object Sources : BottomMenuScreen(
        route= Screens.SOURCES.name,
        icon = Icons.Outlined.Abc,
        title = "Sources"
    )
}
