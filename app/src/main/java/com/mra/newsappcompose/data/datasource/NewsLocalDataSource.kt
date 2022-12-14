package com.mra.newsappcompose.data.datasource

import android.content.Context
import com.mra.newsappcompose.core.base.BaseDataSource
import com.mra.newsappcompose.data.models.CategoryModel
import com.mra.newsappcompose.data.remote.api.ApiService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.math.MathContext

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class NewsLocalDataSource(private val mContext: Context) :
    BaseDataSource(mContext) {

    suspend fun getCategories() = flow {
        val categories = arrayListOf(
            CategoryModel(
                imageUrl = "https://engineerit93.ir/files/news/category/general.png",
                name = "General",
                title = "general"
            ),
            CategoryModel(
                imageUrl = "https://engineerit93.ir/files/news/category/thchnology.png",
                name = "Technology",
                title = "technology"
            ),
            CategoryModel(
                imageUrl = "https://engineerit93.ir/files/news/category/business.png",
                name = "Business",
                title = "business"
            ),
            CategoryModel(
                imageUrl = "https://engineerit93.ir/files/news/category/entertainment.png",
                name = "Entertainment",
                title = "entertainment"
            ),
            CategoryModel(
                imageUrl = "https://engineerit93.ir/files/news/category/health.png",
                name = "Health",
                title = "health"
            ),
            CategoryModel(
                imageUrl = "https://engineerit93.ir/files/news/category/science.png",
                name = "Science",
                title = "science"
            ),
            CategoryModel(
                imageUrl = "https://engineerit93.ir/files/news/category/sport.png",
                name = "Sports",
                title = "sports"
            ),
        )
        emit(categories)
    }

}