package com.mra.newsappcompose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

@Composable
fun LoadingScreen(visible:Boolean) {

    Column(modifier = Modifier.fillMaxSize().alpha(if (visible) 1f else 0f)) {
        Text(text = "Please waite")
    }

}