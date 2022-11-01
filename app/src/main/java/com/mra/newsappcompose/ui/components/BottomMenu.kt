package com.mra.newsappcompose.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mra.newsappcompose.ui.main.MainBottomMenuScreen
import com.mra.newsappcompose.R
import com.mra.newsappcompose.global.objects.MainBottomBarData
import com.mra.newsappcompose.ui.theme.GRAY_10

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */


@SuppressLint("UnrememberedMutableState")
@Composable
fun BottomMenu(navController: NavHostController, bottomBarState: Boolean) {

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


        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val currentRoute =
            navBackStackEntry?.destination?.route ?: MainBottomMenuScreen.NewsList.route

        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(105.dp)
                    .padding(
                        end = 25.dp,
                        start = 25.dp,
                        bottom = 25.dp,
                        top = 10.dp
                    ),
                backgroundColor = Color.White,
                elevation = 4.dp,
                shape = RoundedCornerShape(25.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 8.dp,
                            bottom = 8.dp
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    MainBottomBarData.menuItems.forEach {
                        AddItem(
                            screen = it,
                            currentDestination = currentDestination,
                            navController = navController
                        )
                    }

                }
            }


        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddItem(
    screen: MainBottomMenuScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val contentColor =
        if (!selected) GRAY_10 else MaterialTheme.colors.primary

    Card(modifier = Modifier
        .fillMaxHeight()
        .aspectRatio(1f),
        elevation = 0.dp,
        shape = RoundedCornerShape(12.dp),
        onClick = {
            if (!selected){
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                Icon(
                    painter = painterResource(id = screen.icon),
                    contentDescription = "icon",
                    tint = contentColor
                )

                AnimatedVisibility(visible = selected) {
                    Card(
                        modifier = Modifier
                            .height(5.dp)
                            .width(5.dp),
                        backgroundColor = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(10.dp)
                    ) {

                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomMenu(
        rememberNavController(),
        true
    )
}