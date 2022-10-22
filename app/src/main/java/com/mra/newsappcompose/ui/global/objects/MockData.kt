package com.mra.newsappcompose.ui.global.objects

import android.icu.text.SimpleDateFormat
import android.os.Build
import com.mra.newsappcompose.R
import com.mra.newsappcompose.ui.data.models.NewsModel
import java.util.*

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
object MockData {

    val newsList = listOf(
        NewsModel(
            id =1,
            image = R.drawable.news_1,
            author = "Mohammadreza Allahgholi",
            title = "News 1 added to list",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            publishedAt = "2022-10-22T12:39:40Z"
        ),  NewsModel(
            id =2,
            image = R.drawable.news_2,
            author = "Mohammadreza Allahgholi",
            title = "News 2 added to list",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            publishedAt = "2022-10-22T12:39:40Z"
        ),  NewsModel(
            id =3,
            image = R.drawable.news_3,
            author = "Mohammadreza Allahgholi",
            title = "News 3 added to list",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            publishedAt = "2022-10-22T12:39:40Z"
        ),  NewsModel(
            id =4,
            image = R.drawable.news_4,
            author = "Mohammadreza Allahgholi",
            title = "News 4 added to list",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            publishedAt = "2022-10-22T12:39:40Z"
        ),
        NewsModel(
            id =5,
            image = R.drawable.news_5,
            author = "Mohammadreza Allahgholi",
            title = "News 5 added to list",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            publishedAt = "2022-10-22T12:39:40Z"
        ),
        NewsModel(
            id =6,
            image = R.drawable.news_6,
            author = "Mohammadreza Allahgholi",
            title = "News 6 added to list",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            publishedAt = "2022-10-22T12:39:40Z"
        ),
        NewsModel(
            id =7,
            image = R.drawable.news_7,
            author = "Mohammadreza Allahgholi",
            title = "News 7 added to list",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            publishedAt = "2022-10-22T12:39:40Z"
        ),
        NewsModel(
            id =8,
            image = R.drawable.news_8,
            author = "Mohammadreza Allahgholi",
            title = "News 8 added to list",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            publishedAt = "2022-10-22T12:39:40Z"
        ),
    )

    fun getNews(id:Int?) = newsList.first{it.id == id}

    fun String.getDate():Date{
        return if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssxx",Locale.ENGLISH).parse(this)
        }else{
            java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssxx",Locale.ENGLISH).parse(this)!!
        }
    }

    fun Date.getFullDate():String{
        val calendar = Calendar.getInstance()
        calendar.time = this

       return "${calendar.get(Calendar.YEAR)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DAY_OF_MONTH)}"
    }
}