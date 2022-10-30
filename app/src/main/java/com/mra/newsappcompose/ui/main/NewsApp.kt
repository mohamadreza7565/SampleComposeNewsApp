package com.mra.newsappcompose.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mra.newsappcompose.ui.components.BottomMenu
import com.mra.newsappcompose.data.models.ArticlesModel
import com.mra.newsappcompose.data.models.Source
import com.mra.newsappcompose.global.objects.MainBottomBarData
import com.mra.newsappcompose.ui.categories.Categories
import com.mra.newsappcompose.ui.newsdetails.DetailsScreen
import com.mra.newsappcompose.ui.newslist.ListNews
import com.mra.newsappcompose.ui.sources.Sources
import com.mra.newsappcompose.global.ScreenConst
import com.mra.newsappcompose.ui.components.LoadingScreen

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
        ) { mustBeShowLoading ->
            loadingState = mustBeShowLoading
        }
        LoadingScreen(
            modifier = Modifier.padding(innerPadding),
            visible = loadingState
        )

    }
}


@Composable
fun Navigation(
    modifier: Modifier,
    navController: NavHostController,
    mLoadingListener: (Boolean) -> Unit
) {


    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ScreenConst.Home.NEWS_LIST,
    ) {

        bottomNavigation(
            navController,
            mLoadingListener
        )

        composable(
            route = ScreenConst.NEWS_DETAILS
        ) { navBackStackEntry ->

            navController.previousBackStackEntry?.savedStateHandle?.get<ArticlesModel>("article")
                ?.let {
                    DetailsScreen(
                        navController = navController,
                        article = it,
                        scrollState = rememberScrollState()
                    )
                }


        }

    }
}


fun NavGraphBuilder.bottomNavigation(
    navController: NavHostController,
    mLoadingListener: (Boolean) -> Unit
) {



    composable(MainBottomMenuScreen.NewsList.route) {
        ListNews(
            navController = navController,
            lazyListState = rememberLazyListState(),
            mLoadingListener = mLoadingListener
        )
    }
    composable(MainBottomMenuScreen.Catrgories.route) {
        Categories(
            navController = navController,
            lazyListState = rememberLazyGridState(),
            mLoadingListener = mLoadingListener
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
