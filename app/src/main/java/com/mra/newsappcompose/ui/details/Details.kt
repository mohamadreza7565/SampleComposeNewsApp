package com.mra.newsappcompose.ui.details

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mra.newsappcompose.R
import com.mra.newsappcompose.data.models.ArticlesModel
import com.mra.newsappcompose.global.objects.ScreenConst
import com.mra.newsappcompose.global.getDate
import com.mra.newsappcompose.global.getFullDate

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

fun NavController.openNewsDetails(article: ArticlesModel) {
    currentBackStackEntry?.savedStateHandle?.set(
        "article",
        article
    )
    navigate(ScreenConst.NEWS_DETAILS)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Details(navController: NavController, article: ArticlesModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ToolbarView(navController)
        },
        content = {
            ColumDetails(
                article
            )
        }
    )
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



                    })
                {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Default.Share),
                        contentDescription = "",
                        tint = Color.Black,
                    )
                }
            }

        }
    }
}

@Composable
private fun ColumDetails(
    article: ArticlesModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .verticalScroll(rememberScrollState())
    ) {


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .defaultMinSize(minHeight = 250.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = 0.dp,
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = article.urlToImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 12.dp,
                    top = 12.dp
                ),
            text = article.title ?: "None title",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 18.sp
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )


        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 12.dp,
                    top = 12.dp
                ),
            text = article.description ?: "description"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 12.dp,
                    top = 12.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoWithIcon(
                R.drawable.ic_source,
                article.source.name ?: "None source"
            )
            InfoWithIcon(
                R.drawable.ic_date,
                article.publishedAt?.getDate()?.getFullDate() ?: "None date"
            )
        }

    }
}

@Composable
fun InfoWithIcon(icon: Int, info: String) {
    Row(
        modifier = Modifier.padding(
            end = 16.dp,
            start = 16.dp
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier
                .height(25.dp)
                .width(25.dp)
                .padding(end = 8.dp),
            model = icon,
            contentDescription = "",
            colorFilter = ColorFilter.tint(color = colorResource(id = R.color.grey_700))
        )
        Text(
            text = info,
            style = TextStyle(fontSize = 12.sp)
        )
    }
}
