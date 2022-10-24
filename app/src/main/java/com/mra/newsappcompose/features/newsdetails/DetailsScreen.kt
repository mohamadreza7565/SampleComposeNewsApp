package com.mra.newsappcompose.features.newsdetails

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.mra.newsappcompose.data.enums.Screens
import com.mra.newsappcompose.data.models.NewsModel
import com.mra.newsappcompose.global.objects.MockData
import com.mra.newsappcompose.R
import com.mra.newsappcompose.data.models.ArticlesModel
import com.mra.newsappcompose.global.objects.MockData.getDate
import com.mra.newsappcompose.global.objects.MockData.getFullDate

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

fun NavController.openNewsDetails(item: ArticlesModel) {
    navigate("${Screens.DETAILS_NEWS.name}?item=${Uri.encode(Gson().toJson(item))}")
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, scrollState: ScrollState, article: ArticlesModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = colorResource(id = R.color.purple_500)
            ) {
                Row(
                    modifier = Modifier.padding(
                        end = 16.dp,
                        start = 16.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier
                            .height(36.dp)
                            .width(36.dp),
                    onClick = {
                        navController.popBackStack()
                    })
                    {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Default.ArrowBack),
                            contentDescription = "",
                            tint = Color.White,
                        )
                    }

                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = article.title?:"title",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        },
        content = {
            ColumDetails(
                scrollState,
                article
            )
        }
    )
}

@Composable
private fun ColumDetails(
    scrollState: ScrollState,
    article: ArticlesModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(id = R.drawable.news_1),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoWithIcon(
                Icons.Default.Edit,
                article.author?:"author"
            )
            InfoWithIcon(
                Icons.Default.DateRange,
                article.publishedAt?.getDate()?.getFullDate()?:"2022/24/10"
            )
        }

        Text(
            modifier = Modifier.padding(8.dp),
            text = article.description?:"description"
        )
    }
}

@Composable
fun InfoWithIcon(icon: ImageVector, info: String) {
    Row(
        modifier = Modifier.padding(end=16.dp, start = 16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.padding(end = 8.dp),
            painter = rememberVectorPainter(image = icon),
            contentDescription = "",
            tint = colorResource(id = R.color.purple_500)
        )
        Text(
            text = info,
            style = TextStyle(fontSize = 12.sp)
        )
    }
}
