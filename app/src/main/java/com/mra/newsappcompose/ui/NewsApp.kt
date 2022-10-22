package com.mra.newsappcompose.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mra.newsappcompose.ui.components.BottomMenu
import com.mra.newsappcompose.ui.data.enums.Screens
import com.mra.newsappcompose.ui.global.BottomMenuScreen
import com.mra.newsappcompose.ui.global.objects.MockData
import com.mra.newsappcompose.ui.screen.Categories
import com.mra.newsappcompose.ui.screen.DetailsScreen
import com.mra.newsappcompose.ui.screen.ListNews
import com.mra.newsappcompose.ui.screen.Sources
import com.mra.newsappcompose.R

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
    Scaffold(
        bottomBar = {
            BottomMenu(navController = navController)
        },
    ) { innerPadding ->
        Navigation(
            modifier= Modifier.padding(innerPadding),
            navController = navController,
            scrollState = scrollState
        )
    }
}

@Composable
fun Navigation(modifier:Modifier,navController: NavHostController, scrollState: ScrollState) {
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
    composable(BottomMenuScreen.NewsList.route) {
        ListNews(navController = navController)
    }
    composable(BottomMenuScreen.Catrgories.route) {
        Categories(navController = navController)
    }
    composable(BottomMenuScreen.Sources.route) {
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
