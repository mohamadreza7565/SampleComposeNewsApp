package com.mra.newsappcompose.ui.newslist

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mra.newsappcompose.R
import com.mra.newsappcompose.core.base.BaseApiDataState
import com.mra.newsappcompose.core.base.BaseApiResult
import com.mra.newsappcompose.data.models.ArticlesModel
import com.mra.newsappcompose.ui.newsdetails.openNewsDetails
import com.mra.newsappcompose.global.getDate
import com.mra.newsappcompose.global.getFullDate
import com.mra.newsappcompose.ui.components.ErrorScreen
import org.koin.androidx.compose.getViewModel

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListNews(
    lazyListState: LazyListState,
    navController: NavHostController,
    mViewModel: NewsListViewModel = getViewModel(),
    mLoadingListener: (Boolean) -> Unit
) {


    LaunchedEffect(Unit) {
        mViewModel.getNews()
    }

    if (mViewModel.newses is BaseApiDataState.Loading){
        mLoadingListener.invoke(true)
    }else{
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
                        data = it,
                        navController = navController
                    )
                }

            }

            is BaseApiDataState.Error -> {
                ErrorScreen(
                    visible = errorState,
                    exception = (mViewModel.newses as BaseApiDataState.Error).exception
                ) {
                    mViewModel.getNews()
                }

            }
            else -> {}
        }

    }
}

@Composable
private fun ToolbarView() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 18.dp,
                    bottom = 12.dp,
                    top = 22.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .clip(CircleShape),
                model = R.drawable.ic_avatar,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "10 Jan 2022",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    fontSize = 18.sp
                )
            )
        }

    }
}

@Composable
private fun NewsList(
    lazyListState: LazyListState,
    data: MutableList<ArticlesModel>,
    navController: NavHostController
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            state = lazyListState
        ) {

            item {

                ToolbarView()

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = "Breaking news",
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 35.sp,
                        color = Color.Black
                    )
                )

                BannerView(item = data[9],
                    onItemClick = {
                        navController.openNewsDetails(it)
                    })
            }

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
fun BannerView(item: ArticlesModel, onItemClick: (ArticlesModel) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(340.dp)
            .clickable {
                onItemClick.invoke(item)
            },
    ) {

        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RoundedCornerShape(20.dp),
            elevation = 0.dp,
            backgroundColor = colorResource(id = R.color.white)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = 0.dp,
                ) {

                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize(),
                        model = item.urlToImage,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )

                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = item.title ?: "None title",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 18.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            end = 12.dp,
                            start = 12.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        AsyncImage(
                            modifier = Modifier
                                .height(35.dp)
                                .width(35.dp),
                            model = R.drawable.ic_source,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = item.source.name ?: "None author"
                        )

                    }

                    Text(text = item.publishedAt?.getDate()?.getFullDate() ?: "None date")

                }

            }

        }

    }


}

@Composable
fun NewsItems(item: ArticlesModel, onItemClick: (ArticlesModel) -> Unit) {

    Card(
        modifier = Modifier
            .height(150.dp)
            .padding(10.dp)
            .clickable {
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
                    .height(150.dp)
                    .width(150.dp),
                elevation = 0.dp,
                shape = RoundedCornerShape(20.dp)
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
                    text = item.title ?: "title",
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
                        .fillMaxWidth()
                        .padding(
                            end = 12.dp,
                            start = 12.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        AsyncImage(
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp),
                            model = R.drawable.ic_source,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = colorResource(id = R.color.grey_600))
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = item.source.name ?: "None source",
                            style = TextStyle(fontSize = 14.sp)
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
