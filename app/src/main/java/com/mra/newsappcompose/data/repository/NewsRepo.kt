package com.mra.newsappcompose.data.repository

import android.icu.text.CaseMap.Title
import com.mra.newsappcompose.data.datasource.NewsLocalDataSource
import com.mra.newsappcompose.data.datasource.NewsRemoteDataSource
import kotlinx.coroutines.flow.collect

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class NewsRepo(
    private val mRemoteDataSource: NewsRemoteDataSource,
    private val mLocalDataSource: NewsLocalDataSource
) {

    suspend fun getNews(source: String? = null) = mRemoteDataSource.getNews(source)

    suspend fun getNews(category: String? = null, title: String) = mRemoteDataSource.getNews(
        category = category,
        title = title
    )

    suspend fun getSources(category: String? = null) = mRemoteDataSource.getSources(
        category = category
    )

    suspend fun getCategories() = mLocalDataSource.getCategories()

}