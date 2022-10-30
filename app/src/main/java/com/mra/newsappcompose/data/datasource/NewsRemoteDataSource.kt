package com.mra.newsappcompose.data.datasource

import android.content.Context
import com.mra.newsappcompose.core.base.BaseDataSource
import com.mra.newsappcompose.data.remote.api.ApiService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.math.MathContext

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class NewsRemoteDataSource(private val mContext: Context, private val mApiService: ApiService) :
    BaseDataSource(mContext) {

    suspend fun getNews(source: String? ) = flow {
        callApi {
            mApiService.getNews(source)
        }.collect {
            emit(it)
        }
    }

    suspend fun getNews(category: String?, title: String?) = flow {
        callApi {
            mApiService.getNews(
                category = category,
                title = title
            )
        }.collect {
            emit(it)
        }
    }

    suspend fun getSources(category: String? ) = flow {
        callApi {
            mApiService.getSources(
                category = category
            )
        }.collect {
            emit(it)
        }
    }

}