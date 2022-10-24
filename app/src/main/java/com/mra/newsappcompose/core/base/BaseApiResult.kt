package com.mra.newsappcompose.core.base


import com.google.gson.annotations.SerializedName

data class BaseApiResult<out T>(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: T
)