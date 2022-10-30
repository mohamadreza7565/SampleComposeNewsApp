package com.mra.newsappcompose.data.remote.api

import com.mra.newsappcompose.core.base.BaseApiResult
import com.mra.newsappcompose.data.models.ArticlesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
interface ApiService {

    @GET("top-headlines?sortBy=publishedAt&language=en&apiKey=07c81529ef0645269b2ad5834abb484e")
    suspend fun getNews(
        @Query("sources") sources: String?,
    ): Response<BaseApiResult<MutableList<ArticlesModel>>>

    @GET("top-headlines?sortBy=publishedAt&apiKey=07c81529ef0645269b2ad5834abb484e")
    suspend fun getNews(
        @Query("q") title: String?,
        @Query("category") category: String?,
    ): Response<BaseApiResult<MutableList<ArticlesModel>>>

    @GET("top-headlines/sources?apiKey=07c81529ef0645269b2ad5834abb484e")
    suspend fun getSources(
        @Query("category") category: String?,
    ): Response<BaseApiResult<MutableList<ArticlesModel>>>

}