package com.mra.newsappcompose.core.di


import com.mra.newsappcompose.ui.categories.CategoriesViewModel
import com.mra.newsappcompose.ui.newslist.NewsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewsListViewModel(get()) }
    viewModel { CategoriesViewModel(get()) }
}

