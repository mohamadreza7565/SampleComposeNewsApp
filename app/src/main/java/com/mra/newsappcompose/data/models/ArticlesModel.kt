package com.mra.newsappcompose.data.models


import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ArticlesModel(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
):Parcelable


@Parcelize
data class Source(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
): Parcelable
