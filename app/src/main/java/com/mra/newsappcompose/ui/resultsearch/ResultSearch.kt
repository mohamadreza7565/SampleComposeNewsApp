package com.mra.newsappcompose.ui.resultsearch

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.mra.newsappcompose.data.models.ArticlesModel
import com.mra.newsappcompose.global.objects.ScreenConst

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

fun NavController.openResultSearch(query: String? = null, category: String? = null) {

    if (query.isNullOrEmpty() && category.isNullOrEmpty()) {
        throw Exception("query and category cannot be empty at the same time")
    }

    currentBackStackEntry?.savedStateHandle?.apply {
        set(
            "query",
            query
        )
        set(
            "category",
            category
        )
    }
    navigate(ScreenConst.RESULT_SEARCH)
}

@Composable
fun ResultSearch(
    navController: NavController,
    scrollState: ScrollState,
    query: String?,
    category: String?,
    mLoadingListener: (Boolean) -> Unit,
) {
}