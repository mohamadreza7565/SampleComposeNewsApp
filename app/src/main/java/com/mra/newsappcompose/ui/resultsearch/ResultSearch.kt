package com.mra.newsappcompose.ui.resultsearch

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mra.newsappcompose.R
import com.mra.newsappcompose.core.base.BaseApiDataState
import com.mra.newsappcompose.core.base.BaseApiResult
import com.mra.newsappcompose.data.models.ArticlesModel
import com.mra.newsappcompose.global.getDate
import com.mra.newsappcompose.global.getFullDate
import com.mra.newsappcompose.global.objects.ScreenConst
import com.mra.newsappcompose.global.objects.StateItems
import com.mra.newsappcompose.ui.components.ErrorScreen
import com.mra.newsappcompose.ui.details.openNewsDetails
import com.mra.newsappcompose.ui.theme.GRAY_10
import org.koin.androidx.compose.getViewModel

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
            if (category == "all") null else category
        )
    }
    navigate(ScreenConst.RESULT_SEARCH)
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ResultSearch(
    lazyListState: LazyListState,
    query: String?,
    category: String?,
    navController: NavHostController,
    mViewModel: ResultSearchViewModel = getViewModel(),
    mLoadingListener: (Boolean) -> Unit
) {


    LaunchedEffect(Unit) {
        mViewModel.getNews(
            query = query,
            category = category
        )
    }

    if (mViewModel.newses is BaseApiDataState.Loading) {
        mLoadingListener.invoke(true)
    } else {
        mLoadingListener.invoke(false)
    }
    val errorState = mViewModel.newses is BaseApiDataState.Error

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {

        when (mViewModel.newses) {
            is BaseApiDataState.Success -> {

                (mViewModel.newses as BaseApiDataState.Success<BaseApiResult<MutableList<ArticlesModel>>>).data?.articles?.let {
                    NewsList(
                        lazyListState = lazyListState,
                        newses = it,
                        navController = navController,
                    )
                }

            }

            is BaseApiDataState.Error -> {
                ErrorScreen(
                    visible = errorState,
                    exception = (mViewModel.newses as BaseApiDataState.Error).exception
                ) {
                    mViewModel.getNews(
                        query = query,
                        category = category
                    )
                }

            }
            else -> {}
        }

    }
}


@Composable
private fun ToolbarView(navController: NavController) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    end = 16.dp,
                    start = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            Card(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .clip(CircleShape),
                elevation = 8.dp,
                backgroundColor = Color.White
            ) {
                IconButton(
                    modifier = Modifier
                        .height(36.dp)
                        .width(36.dp)
                        .clip(CircleShape),
                    onClick = {
                        navController.popBackStack()
                    })
                {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.ArrowBack),
                        contentDescription = "",
                        tint = Color.Black,
                    )
                }

            }

        }
    }
}

@Composable
private fun NewsList(
    lazyListState: LazyListState,
    newses: MutableList<ArticlesModel>,
    navController: NavHostController
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ToolbarView(navController)

        Spacer(modifier = Modifier.width(12.dp))

        LazyColumn(
            state = lazyListState,
            contentPadding = PaddingValues(bottom = 150.dp)
        ) {
            items(newses) { item ->
                NewsItems(
                    item = item,
                    onItemClick = {
                        navController.openNewsDetails(it)
                    })
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsItems(item: ArticlesModel, onItemClick: (ArticlesModel) -> Unit) {

    Card(
        modifier = Modifier
            .height(150.dp)
            .padding(10.dp),
        onClick = {
            onItemClick.invoke(item)
        },
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            Card(
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp),
                elevation = 0.dp,
                shape = RoundedCornerShape(20.dp),
                backgroundColor = GRAY_10
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = item.urlToImage,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_place_holder),
                    error = painterResource(R.drawable.ic_place_holder)
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = item.title ?: "None title",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        AsyncImage(
                            modifier = Modifier
                                .height(15.dp)
                                .width(15.dp),
                            model = R.drawable.ic_source,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = colorResource(id = R.color.grey_600))
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = item.source.name ?: "None source",
                            style = TextStyle(fontSize = 12.sp)
                        )

                    }

                    Text(
                        text = item.publishedAt?.getDate()?.getFullDate() ?: "None date",
                        style = TextStyle(fontSize = 10.sp)
                    )

                }


            }

        }
    }

}