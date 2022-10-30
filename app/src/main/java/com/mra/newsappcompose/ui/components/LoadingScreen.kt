package com.mra.newsappcompose.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

@Composable
fun LoadingScreen(
    modifier: Modifier,
    visible: Boolean
) {

    AnimatedVisibility(
        modifier = modifier,
        visible = visible
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }


}