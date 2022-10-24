package com.mra.newsappcompose.data.repository

import com.mra.newsappcompose.data.datasource.NewsRemoteDataSource
import kotlinx.coroutines.flow.collect

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class NewsRepo(private val mRemoteDataSource: NewsRemoteDataSource) {

    suspend fun getTeslaNews() = mRemoteDataSource.getTeslaNews()

}