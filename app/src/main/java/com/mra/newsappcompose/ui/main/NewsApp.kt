package com.mra.newsappcompose.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mra.newsappcompose.ui.components.BottomMenu
import com.mra.newsappcompose.data.models.ArticlesModel
import com.mra.newsappcompose.global.objects.MainBottomBarData
import com.mra.newsappcompose.ui.details.Details
import com.mra.newsappcompose.ui.home.Home
import com.mra.newsappcompose.ui.profile.Sources
import com.mra.newsappcompose.global.objects.ScreenConst
import com.mra.newsappcompose.ui.search.Search
import com.mra.newsappcompose.ui.components.LoadingScreen
import com.mra.newsappcompose.ui.resultsearch.ResultSearch

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
    var loadingState by remember { mutableStateOf(false) }
    var routeState by remember { mutableStateOf(ScreenConst.Main.Home) }

    Scaffold(
        bottomBar = {
            BottomMenu(
                navController = navController,
                bottomBarState = bottomBarState
            )
        },
    ) { innerPadding ->

        Navigation(
            navController = navController,
        ) { mustBeShowLoading ->
            routeState = navController.currentDestination?.route!!
            loadingState = mustBeShowLoading
        }
        LoadingScreen(
            navController = navController,
            route = routeState,
            modifier = Modifier.padding(innerPadding),
            visible = loadingState
        )

    }
}


@Composable
fun Navigation(
    navController: NavHostController,
    mLoadingListener: (Boolean) -> Unit
) {

    val resultSearchLazyListState = rememberLazyListState()

    NavHost(
        navController = navController,
        startDestination = ScreenConst.Main.Home,
    ) {

        bottomNavigation(
            navController,
            mLoadingListener
        )

        resultSearch(
            lazyListState = resultSearchLazyListState,
            navController,
            mLoadingListener
        )


        composable(
            route = ScreenConst.NEWS_DETAILS
        ) { navBackStackEntry ->

            navController.previousBackStackEntry?.savedStateHandle?.get<ArticlesModel>("article")
                ?.let {
                    Details(
                        navController = navController,
                        article = it
                    )
                }


        }

    }
}


fun NavGraphBuilder.resultSearch(
    lazyListState: LazyListState,
    navController: NavHostController,
    mLoadingListener: (Boolean) -> Unit
) {

    composable(
        route = ScreenConst.RESULT_SEARCH
    ) { navBackStackEntry ->

        val query = navController.previousBackStackEntry?.savedStateHandle?.get<String>("query")
        val category =
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("category")

        if (query.isNullOrEmpty() && category.isNullOrEmpty()) {
            return@composable
        }

        ResultSearch(
            navController = navController,
            lazyListState = lazyListState,
            query = query,
            category = category,
            mLoadingListener = mLoadingListener
        )

    }

}

fun NavGraphBuilder.bottomNavigation(
    navController: NavHostController,
    mLoadingListener: (Boolean) -> Unit
) {


    composable(MainBottomMenuScreen.NewsList.route) {
        Home(
            navController = navController,
            lazyListState = rememberLazyListState(),
            mLoadingListener = mLoadingListener
        )
    }

    composable(MainBottomMenuScreen.Search.route) {
        Search(
            navController = navController,
            mLoadingListener = mLoadingListener,
        )
    }

    composable(MainBottomMenuScreen.Sources.route) {
        Sources(
            navController = navController
        )
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
