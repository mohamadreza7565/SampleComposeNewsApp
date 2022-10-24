package com.mra.newsappcompose.features.newslist

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mra.newsappcompose.R
import com.mra.newsappcompose.core.base.BaseApiDataState
import com.mra.newsappcompose.core.base.BaseApiResult
import com.mra.newsappcompose.data.models.ArticlesModel
import com.mra.newsappcompose.data.models.NewsModel
import com.mra.newsappcompose.global.objects.MockData
import com.mra.newsappcompose.features.newsdetails.openNewsDetails
import com.mra.newsappcompose.ui.components.LoadingScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.inject

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListNews(
    lazyListState : LazyListState,
    navController: NavController,
    mViewModel: NewsListViewModel = getViewModel(),
    mContext: Context = get()
) {


    LaunchedEffect(Unit) {
        mViewModel.getTeslaNews()
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(id = R.color.purple_500)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Sample compose news application",
                        style = TextStyle(
                            color = colorResource(id = R.color.white),
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp
                        )
                    )
                }
            }
        },
    ) {


        LoadingScreen(mViewModel.newses == BaseApiDataState.Loading)

        when (mViewModel.newses) {
            is BaseApiDataState.Success -> {

                (mViewModel.newses as BaseApiDataState.Success<BaseApiResult<MutableList<ArticlesModel>>>).data?.articles?.let {
                    NewsList(
                        lazyListState = lazyListState,
                        data = it,
                        navController = navController
                    )
                }

            }

            else -> {}
        }

    }
}

@Composable
private fun NewsList(lazyListState:LazyListState,data: MutableList<ArticlesModel>, navController: NavController) {



    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            state = lazyListState
        ) {
            items(data) { item ->
                NewsItems(
                    item = item,
                    onItemClick = {
                        navController.openNewsDetails(it)
                    })
            }
        }
    }
}

@Composable
fun NewsItems(item: ArticlesModel, onItemClick: (ArticlesModel) -> Unit) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .padding(8.dp)
            .clickable {
                onItemClick.invoke(item)
            },
        shape = RoundedCornerShape(12.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val gradient = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = size.height / 3,
                        endY = size.height
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(
                            gradient,
                            blendMode = BlendMode.Multiply
                        )
                    }
                },
            painter = painterResource(id = R.drawable.news_1),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = item.publishedAt?:"publishedAt",
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = item.title?:"title",
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ListNewsPreview() {
    ListNews(rememberLazyListState(),rememberNavController())
}