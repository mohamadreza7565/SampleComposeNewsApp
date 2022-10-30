package com.mra.newsappcompose.core.di.interceptor

import android.util.Log
import com.mra.newsappcompose.BuildConfig
import com.mra.newsappcompose.global.objects.GlobalFunction
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit


/**
 * Create by Mohammadreza Allahgholi &&   Javad Vatandoost on 12/8/2020
 * Sites: http://Jvatan.ir && http://JavadVatan.ir
 *
 * This interceptor will be called both if the network is available and if the network is not available
 */

internal class OfflineInterceptor : Interceptor {
    private val HEADER_CACHE_CONTROL = "Cache-Control"
    private val HEADER_PRAGMA = "Pragma"

    override fun intercept(chain: Interceptor.Chain): Response {


        var request: Request = chain.request()
        val response = chain.proceed(request)

        if (BuildConfig.DEBUG) {
            val source = response.body!!.source()
            source.request(Long.MAX_VALUE) // Buffer the entire body.

            val buffer: Buffer = source.buffer()
            val responseBodyString: String = buffer.clone().readString(Charset.forName("UTF-8"))
            Log.i(
                "TAG",
                "Http response -> $responseBodyString"
            )
        }


        // prevent caching when network is on. For that we use the "networkInterceptor"
        if (!GlobalFunction.isNetworkAvailable) {
            val cacheControl: CacheControl = CacheControl.Builder()
                .maxStale(30, TimeUnit.DAYS)
                .build()

            request = request.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .cacheControl(cacheControl)
                .build()
        }
        return response
    }
}
