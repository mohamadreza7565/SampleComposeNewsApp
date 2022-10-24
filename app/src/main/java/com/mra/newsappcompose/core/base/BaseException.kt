package com.mra.newsappcompose.core.base

import androidx.annotation.StringRes

class BaseException(
    val type: Type,
    val serverMessage: String? = null,
    val code: Int? = null,
) : Throwable() {

    enum class Type {
        VALIDATION, SIMPLE, AUTH, INTERNET, SERVER
    }

}