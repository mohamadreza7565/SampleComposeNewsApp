package com.mra.newsappcompose.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.mra.newsappcompose.core.base.BaseApiDataState
import com.mra.newsappcompose.core.base.BaseApiResult
import com.mra.newsappcompose.core.base.BaseViewModel
import com.mra.newsappcompose.data.models.ArticlesModel
import com.mra.newsappcompose.data.models.CategoryModel
import com.mra.newsappcompose.data.repository.NewsRepo
import kotlinx.coroutines.launch

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class SearchViewModel(
    private val mNewsRepo: NewsRepo
) : BaseViewModel() {

    var newses by mutableStateOf<BaseApiDataState<BaseApiResult<MutableList<ArticlesModel>>>>(BaseApiDataState.Loading)
    var categories by mutableStateOf<MutableList<CategoryModel>>(arrayListOf())

    fun getNews(title: String) {

        viewModelScope.launch {
            mNewsRepo.getNews(title = title).collect {
                newses = it
            }
        }

    }

    fun getCategories() {

        viewModelScope.launch {
            mNewsRepo.getCategories().collect {
                it.add(
                    0,
                    CategoryModel(
                        imageUrl = "",
                        title = "all",
                        name = "All"
                    )
                )
                categories = it
            }
        }

    }

}