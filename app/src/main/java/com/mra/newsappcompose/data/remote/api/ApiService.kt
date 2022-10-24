package com.mra.newsappcompose.data.remote.api

import com.mra.newsappcompose.core.base.BaseApiResult
import com.mra.newsappcompose.data.models.ArticlesModel
import retrofit2.Response
import retrofit2.http.GET

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
interface ApiService {

    @GET("everything?q=tesla&sortBy=publishedAt&apiKey=07c81529ef0645269b2ad5834abb484e")
    suspend fun getEverythingOfTesla(): Response<BaseApiResult<MutableList<ArticlesModel>>>

}