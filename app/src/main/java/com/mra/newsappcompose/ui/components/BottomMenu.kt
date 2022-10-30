package com.mra.newsappcompose.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mra.newsappcompose.ui.main.MainBottomMenuScreen
import com.mra.newsappcompose.R
import com.mra.newsappcompose.global.objects.MainBottomBarData

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */


@SuppressLint("UnrememberedMutableState")
@Composable
fun BottomMenu(navController: NavController, bottomBarState: Boolean) {

    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(durationMillis = 350)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(durationMillis = 350)
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            // Expand from the top.
            shrinkTowards = Alignment.Top,
            animationSpec = tween(durationMillis = 350)
        ) + fadeOut(
            // Fade in with the initial alpha of 0.3f.
            animationSpec = tween(durationMillis = 350)
        )
    }


    AnimatedVisibility(
        visible = bottomBarState,
        enter = enterTransition,
        exit = exitTransition
    ) {
        BottomNavigation(
            contentColor = colorResource(id = R.color.purple_500),
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute =
                navBackStackEntry?.destination?.route ?: MainBottomMenuScreen.NewsList.route

            MainBottomBarData.menuItems.forEach {
                BottomNavigationItem(
                    label = {
                        Text(text = it.title)
                    },
                    alwaysShowLabel = false,
                    selectedContentColor = colorResource(id = R.color.purple_500),
                    unselectedContentColor = colorResource(id = R.color.grey_400),
                    selected = currentRoute == it.route,
                    icon = {
                        Icon(
                            painter = rememberVectorPainter(image = it.icon),
                            contentDescription = it.title
                        )
                    },
                    onClick = {
                        navController.navigate(it.route) {
                            navController.graph.startDestinationRoute?.let {
                                popUpTo(it) {
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

}