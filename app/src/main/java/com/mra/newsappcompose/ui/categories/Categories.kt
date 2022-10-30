package com.mra.newsappcompose.ui.categories

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mra.newsappcompose.core.base.BaseApiDataState
import com.mra.newsappcompose.core.base.BaseApiResult
import com.mra.newsappcompose.data.models.ArticlesModel
import com.mra.newsappcompose.data.models.CategoryModel
import com.mra.newsappcompose.ui.components.ErrorScreen
import com.mra.newsappcompose.ui.newslist.NewsListViewModel
import org.koin.androidx.compose.getViewModel

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

@Composable
fun Categories(
    navController: NavController,
    lazyListState: LazyGridState,
    mViewModel: CategoriesViewModel = getViewModel(),
    mLoadingListener: (Boolean) -> Unit
) {


    LaunchedEffect(
        key1 = Unit
    ) {
        mViewModel.getCategories()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {

        CategoryList(
            lazyListState = lazyListState,
            data = mViewModel.categories,
            navController = navController
        )

    }

}

@Composable
fun CategoryList(
    data: MutableList<CategoryModel>,
    lazyListState: LazyGridState,
    navController: NavController
) {

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        state = lazyListState,
        columns = GridCells.Fixed(1),
    ) {

        data.forEachIndexed { index, item ->
            item {
                CategoryItem(item = item)
            }
        }

    }

}

@Composable
fun CategoryItem(item: CategoryModel) {

    Card(
        modifier = Modifier
            .aspectRatio(.75f)
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = item.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }

}