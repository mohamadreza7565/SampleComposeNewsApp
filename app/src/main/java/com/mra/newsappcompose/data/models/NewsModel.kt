package com.mra.newsappcompose.data.models

import com.mra.newsappcompose.R

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
data class NewsModel(
    val id:Int,
    val image:Int = R.drawable.news_1,
    val author:String,
    val title:String,
    val description:String,
    val publishedAt:String,
)
