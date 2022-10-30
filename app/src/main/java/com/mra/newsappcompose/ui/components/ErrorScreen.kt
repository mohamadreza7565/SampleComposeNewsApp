package com.mra.newsappcompose.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mra.newsappcompose.core.base.BaseException

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

@Composable
fun ErrorScreen(visible: Boolean, exception: BaseException, onRetry: (() -> Unit?)? = null) {

    AnimatedVisibility(visible = visible) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = exception.serverMessage ?: "خطا در برقراری ارتباط با سرور")
            onRetry?.let {
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = { onRetry() }) {
                    Text(text = "تلاش مجدد")
                }
            }
        }
    }


}