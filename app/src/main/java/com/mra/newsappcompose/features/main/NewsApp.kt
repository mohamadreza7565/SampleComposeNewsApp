package com.mra.newsappcompose.features.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.google.gson.Gson
import com.mra.newsappcompose.ui.components.BottomMenu
import com.mra.newsappcompose.data.enums.Screens
import com.mra.newsappcompose.data.models.ArticlesModel
import com.mra.newsappcompose.data.models.ArticlesPage_UniqueUserNavType
import com.mra.newsappcompose.global.objects.MainBottomBarData
import com.mra.newsappcompose.global.objects.MockData
import com.mra.newsappcompose.features.categories.Categories
import com.mra.newsappcompose.features.newsdetails.DetailsScreen
import com.mra.newsappcompose.features.newslist.ListNews
import com.mra.newsappcompose.features.sources.Sources

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

    val lazyListState = rememberLazyListState()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.LIST_NEWS.name,
    ) {

        bottomNavigation(
            lazyListState,
            navController
        )
        composable(route = Screens.LIST_NEWS.name) {
            ListNews(
                lazyListState = lazyListState,
                navController = navController
            )
        }

        composable(
            route = "${Screens.DETAILS_NEWS.name}?item={item}",
            arguments = mutableListOf(navArgument("item") { type = ArticlesPage_UniqueUserNavType }),
        ) { navBackStackEntry ->

           val article = navBackStackEntry.arguments?.getParcelable<ArticlesModel>("item")!!

            DetailsScreen(
                navController = navController,
                article = article,
                scrollState = rememberScrollState()
            )
        }

    }
}


fun NavGraphBuilder.bottomNavigation(
    lazyListState: LazyListState,
    navController: NavHostController
) {
    composable(MainBottomMenuScreen.NewsList.route) {
        ListNews(
            navController = navController,
            lazyListState = lazyListState
        )
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
