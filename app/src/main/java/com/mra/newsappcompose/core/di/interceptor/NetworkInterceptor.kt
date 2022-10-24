package com.mra.newsappcompose.core.di.interceptor


import android.util.Log
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**
 * Create by Mohammadreza Allahgholi &&   Javad Vatandoost on 12/8/2020
 * Sites: http://Jvatan.ir && http://JavadVatan.ir
 *
 * This interceptor will be called ONLY if the network is available
 */

internal class NetworkInterceptor : Interceptor {
    private val TAG = "NetworkInterceptor"
    private val HEADER_CACHE_CONTROL = "Cache-Control"
    private val HEADER_PRAGMA = "Pragma"
    private val CACHE_DEFAULT_TIME = 60 // 1 min

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(TAG, "network  interceptor: called.")

        val request = chain.request()
        //        Log.d(TAG, "request: ${request.url().url().toString()}")
        //        Log.d(TAG, "cache header : ${request.header("X-Cache") ?: null}")

        // prevent caching when network is on. For that we use the "networkInterceptor"

        val maxAge = request.header("X-Cache")?.toInt() ?: CACHE_DEFAULT_TIME

        val cacheControl: CacheControl = CacheControl.Builder()
            .maxAge(maxAge, TimeUnit.SECONDS)
            .build()

        val response = chain.proceed(request)

        return response.newBuilder()
            .removeHeader(HEADER_PRAGMA)
            .removeHeader(HEADER_CACHE_CONTROL)
            .header(HEADER_CACHE_CONTROL, cacheControl.toString())
            .build()
    }
}
