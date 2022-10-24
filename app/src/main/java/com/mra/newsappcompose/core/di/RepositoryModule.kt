package com.mra.newsappcompose.core.di


import com.mra.newsappcompose.data.datasource.NewsRemoteDataSource
import com.mra.newsappcompose.data.repository.NewsRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 *
 * Usage:
 *
 * How to call:
 *
 * Useful parameter:
 *
 */
val repositoryModule = module {
    single { NewsRepo(NewsRemoteDataSource(androidContext(), mApiService = get())) }
}