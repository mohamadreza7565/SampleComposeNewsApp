package com.mra.newsappcompose.ui.newslist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.mra.newsappcompose.core.base.BaseApiDataState
import com.mra.newsappcompose.core.base.BaseApiResult
import com.mra.newsappcompose.core.base.BaseViewModel
import com.mra.newsappcompose.data.models.ArticlesModel
import com.mra.newsappcompose.data.repository.NewsRepo
import kotlinx.coroutines.launch

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class NewsListViewModel(private val mRepo: NewsRepo) : BaseViewModel() {

    var newses by mutableStateOf<BaseApiDataState<BaseApiResult<MutableList<ArticlesModel>>>>(BaseApiDataState.Loading)

    fun getNews() {
        viewModelScope.launch {
            mRepo.getNews().collect{
                newses = it
            }
        }

    }

}