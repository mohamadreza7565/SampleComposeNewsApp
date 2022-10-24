package com.mra.newsappcompose.core.di



import com.mra.newsappcompose.core.createBaseNetworkClient
import com.mra.newsappcompose.core.getGson
import com.mra.newsappcompose.data.remote.api.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 *
 * Usage: this methods handle DI
 *
 * How to call: just startActivityForResult koin in application class
 *
 */
private val mGson = getGson()
val baseRetrofit: Retrofit = createBaseNetworkClient(gson = mGson)
val apiService: ApiService = baseRetrofit.create(ApiService::class.java)

/*
val dataBaseModule = module {
    single { AppDatabase(androidContext()) }
}
*/

val gsonModule = module {
    single { mGson }
}

val networkModule = module {
    single { apiService }
}

//val remoteConfigModule = module {
//    single { RemoteConfigRepo(androidContext(), getRemoteConfigInstance()) }
//}
