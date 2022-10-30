package com.mra.newsappcompose.global.objects

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import org.koin.androidx.compose.get
import org.koin.androidx.compose.inject
import org.koin.java.KoinJavaComponent

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
object GlobalFunction {

    val isNetworkAvailable: Boolean
        get() {
            val mContext: Context by KoinJavaComponent.inject(Context::class.java)
            val connectivityManager =
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetworkInfo: NetworkInfo? = null
            activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }


    fun shareText(text: String?) {

        val mContext: Context by KoinJavaComponent.inject(Context::class.java)

        text?.let {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    text
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(
                sendIntent,
                null
            )
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            mContext.startActivity(shareIntent)
        }
    }

    fun openBrowser(link: String?) {
        val mContext: Context by KoinJavaComponent.inject(Context::class.java)
        link?.let {
            var initUrl = it
            if (!initUrl.startsWith("http://") && !initUrl.startsWith("https://"))
                initUrl = "http://" + it

            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(initUrl)
            )
            browserIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            mContext.startActivity(browserIntent)

        }

    }

}