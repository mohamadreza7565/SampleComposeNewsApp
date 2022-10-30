package com.mra.newsappcompose.ui.categories

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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */
class CategoriesViewModel(
    private val mNewsRepo: NewsRepo
) : BaseViewModel() {

    var categories by mutableStateOf<MutableList<CategoryModel>>(arrayListOf())

    fun getCategories() {

        viewModelScope.launch {
            mNewsRepo.getCategories().collect {
                categories = it
            }
        }

    }

}