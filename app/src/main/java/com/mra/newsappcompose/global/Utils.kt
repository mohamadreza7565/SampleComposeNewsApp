package com.mra.newsappcompose.global

import android.icu.text.SimpleDateFormat
import android.os.Build
import java.util.*

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

fun String.getDate(): Date {
    return if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssxx",
            Locale.ENGLISH).parse(this)
    }else{
        java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssxx",
            Locale.ENGLISH).parse(this)!!
    }
}

fun Date.getFullDate():String{
    val calendar = Calendar.getInstance()
    calendar.time = this

    return "${calendar.get(Calendar.YEAR)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DAY_OF_MONTH)}"
}
