package com.mra.newsappcompose.ui.screen.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mra.newsappcompose.ui.components.BottomMenu
import com.mra.newsappcompose.ui.data.enums.Screens
import com.mra.newsappcompose.ui.global.objects.MainBottomBarData
import com.mra.newsappcompose.ui.global.objects.MockData
import com.mra.newsappcompose.ui.screen.categories.Categories
import com.mra.newsappcompose.ui.screen.newsdetails.DetailsScreen
import com.mra.newsappcompose.ui.screen.newslist.ListNews
import com.mra.newsappcompose.ui.screen.sources.Sources

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
@Composable
fun NewsApp() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    MainScreen(
        navController,
        scrollState
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route ?: MainBottomMenuScreen.NewsList.route

    val bottomBarState = MainBottomBarData.menuItems.find { it.route == currentRoute } != null

    Scaffold(
        bottomBar = {
            BottomMenu(
                navController = navController,
                bottomBarState = bottomBarState
            )
        },
    ) { innerPadding ->
        Navigation(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            scrollState = scrollState
        )
    }
}

@Composable
fun Navigation(modifier: Modifier, navController: NavHostController, scrollState: ScrollState) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.LIST_NEWS.name,
    ) {

        bottomNavigation(navController)
        composable(Screens.LIST_NEWS.name) {
            ListNews(navController = navController)
        }

        composable(
            route = "${Screens.DETAILS_NEWS.name}/{newsId}",
            arguments = listOf(navArgument("newsId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("newsId")
            DetailsScreen(
                navController = navController,
                newsData = MockData.getNews(id),
                scrollState = rememberScrollState()
            )
        }

    }
}


fun NavGraphBuilder.bottomNavigation(navController: NavHostController) {
    composable(MainBottomMenuScreen.NewsList.route) {
        ListNews(navController = navController)
    }
    composable(MainBottomMenuScreen.Catrgories.route) {
        Categories(navController = navController)
    }
    composable(MainBottomMenuScreen.Sources.route) {
        Sources(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        navController = rememberNavController(),
        scrollState = rememberScrollState()
    )
}
