package com.mra.newsappcompose.core

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mra.newsappcompose.core.di.interceptor.NetworkInterceptor
import com.mra.newsappcompose.core.di.interceptor.OfflineInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.java.KoinJavaComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * Usage: functions to create network requirements such as okHttpClient
 *
 * How to call: just call [createBaseNetworkClient] in AppInjector
 *
 */


fun createBaseNetworkClient(gson: Gson) = retrofitClient(gson)

private fun retrofitClient(gson: Gson): Retrofit =
    Retrofit.Builder()
        .baseUrl("http://newsapi.org/v2/")
        .client(getOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

fun getOkHttpClient(): OkHttpClient {

    val mContext: Context by KoinJavaComponent.inject(Context::class.java)

    val okHttpClient = OkHttpClient.Builder()
        .cache(getCache(mContext))
        .addNetworkInterceptor(NetworkInterceptor())
        .addInterceptor(OfflineInterceptor())

    setTimeOutToOkHttpClient(okHttpClient)


    return okHttpClient.build()
}

private fun setTimeOutToOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder) =
    okHttpClientBuilder.apply {
        readTimeout(90, TimeUnit.SECONDS)
        connectTimeout(90, TimeUnit.SECONDS)
        writeTimeout(90, TimeUnit.SECONDS)
    }


private fun getCache(mContext: Context) = Cache(getFile(mContext), 10 * 1000 * 1000) //10MB cache;

private fun getFile(mContext: Context) = File(mContext.cacheDir, "okhttp_cache").apply {
    mkdirs()
}

fun getGson() = GsonBuilder().create()

