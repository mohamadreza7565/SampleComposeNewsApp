package com.mra.newsappcompose.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mra.newsappcompose.ui.global.BottomMenuScreen
import com.mra.newsappcompose.R

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */


@Composable
fun BottomMenu(navController: NavController) {

    val menuItems = listOf(
        BottomMenuScreen.NewsList,
        BottomMenuScreen.Catrgories,
        BottomMenuScreen.Sources
    )

    BottomNavigation(
        contentColor = colorResource(id = R.color.white)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route?:BottomMenuScreen.NewsList.route

        menuItems.forEach {
            BottomNavigationItem(
                label = {
                    Text(text = it.title)
                },
                alwaysShowLabel = false,
                selectedContentColor = Color.White,
                unselectedContentColor = colorResource(id = R.color.grey_400),
                selected = currentRoute == it.route,
                icon= {
                    Icon(
                        painter = rememberVectorPainter(image = it.icon),
                        contentDescription = it.title
                    )
                },
                onClick = {
                    navController.navigate(it.route){
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it){
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }

    }

}