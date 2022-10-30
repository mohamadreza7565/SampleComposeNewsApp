package com.mra.newsappcompose.ui.resultsearch

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
import retrofit2.http.Query

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class ResultSearchViewModel(private val mRepo: NewsRepo) : BaseViewModel() {

    var newses by mutableStateOf<BaseApiDataState<BaseApiResult<MutableList<ArticlesModel>>>>(BaseApiDataState.Loading)

    fun getNews(query: String?, category: String?) {
        viewModelScope.launch {
            mRepo.getNews(
                title = query,
                category = category
            ).collect {
                newses = it
            }
        }
    }


}