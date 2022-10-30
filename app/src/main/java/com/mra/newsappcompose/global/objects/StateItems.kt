package com.mra.newsappcompose.global.objects

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.mutableStateOf

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
object StateItems {

    var homeSearchField = mutableStateOf("")
    var homeSearchCategory = mutableStateOf("all")

}