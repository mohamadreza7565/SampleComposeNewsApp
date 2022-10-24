package com.mra.newsappcompose.global.objects

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import org.koin.java.KoinJavaComponent

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
object GlobalFunction {

    val isNetworkAvailable: Boolean
        get() {
            val mContext: Context by KoinJavaComponent.inject(Context::class.java)
            val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetworkInfo: NetworkInfo? = null
            activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

}