package com.mra.newsappcompose.core.di


import com.mra.newsappcompose.ui.details.DetailsViewModel
import com.mra.newsappcompose.ui.search.SearchViewModel
import com.mra.newsappcompose.ui.home.HomeViewModel
import com.mra.newsappcompose.ui.resultsearch.ResultSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { ResultSearchViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}

