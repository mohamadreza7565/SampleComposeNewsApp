package com.mra.newsappcompose.ui.profile

import android.content.Context
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mra.newsappcompose.R
import com.mra.newsappcompose.global.objects.GlobalFunction
import org.koin.core.context.GlobalContext.get

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
@Composable
fun Sources(navController: NavController) {

    Scaffold(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {


            Spacer(modifier = Modifier.height(50.dp))

            Card(
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
                    .clip(CircleShape),
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.primaryVariant,
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = "https://seniorandroid.ir/img/main_photo.jpg",
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.ic_place_holder),
                    error = painterResource(R.drawable.ic_place_holder),
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Android Developer",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Mohammadreza Allahgholi")

            Spacer(modifier = Modifier.height(24.dp))

            val buttonsModifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(end = 16.dp , start = 16.dp, top = 16.dp)

            Button(
                modifier = buttonsModifier,
                onClick = {
                    GlobalFunction.openBrowser("https://github.com/mohamadreza7565/SampleComposeNewsApp")
                }) {
                Text(text = "Application source")
            }


            Button(
                modifier = buttonsModifier,
                onClick = {
                    GlobalFunction.openBrowser("https://seniorandroid.ir")
                }) {
                Text(text = "Website")
            }


            Button(
                modifier = buttonsModifier,
                onClick = {
                    GlobalFunction.openBrowser("https://www.linkedin.com/in/mohamadreza7565")
                }) {
                Text(text = "LinkedIn")
            }


            Button(
                modifier = buttonsModifier,
                onClick = {
                    GlobalFunction.openBrowser("https://github.com/mohamadreza7565")
                }) {
                Text(text = "Github")
            }
        }
    }

}