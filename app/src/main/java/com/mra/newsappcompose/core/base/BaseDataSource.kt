package com.mra.newsappcompose.core.base

import android.content.Context
import android.util.Log
import com.mra.newsappcompose.global.objects.GlobalFunction
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import retrofit2.Response


abstract class BaseDataSource(val context: Context) {

    private suspend fun <T> getResult(call: suspend () -> Response<BaseApiResult<T>>): BaseApiDataState<BaseApiResult<T>> {
        try {

            val response = call()

            if (response.isSuccessful) {
                val body = response.body()
                return BaseApiDataState.Success(body)
            }

            return BaseApiDataState.Error(parseErrorModel(response.body()!!))
        } catch (e: Exception) {
            e.printStackTrace()
            logE(" call Api crashed - Exception happened in BaseDataSource.kt:27 -> \n" +
                    "\n======================================\\/========================================= \n $e \n " +
                    " ====================================/\\=======================================")
            return if (!GlobalFunction.isNetworkAvailable)
                BaseApiDataState.Error(BaseException(BaseException.Type.INTERNET,"خطا در اتصال به اینترنت",0))
            else
                BaseApiDataState.Error(BaseException(BaseException.Type.INTERNET,e.message,0))
        }
    }

    private fun <T> parseErrorModel(response: BaseApiResult<T>): BaseException {
        return BaseException(
            BaseException.Type.SERVER,
            response.message!!,
            response.code!!
        )
    }

    /**
     * get Result With Exponential Backoff Strategy
     */
    protected fun <T> callApi(call: suspend () -> Response<BaseApiResult<T>>) = flow {
            emit(BaseApiDataState.Loading)
            val response = getResult(call)
            emit(response)
    }

    fun logE(where: String) {
        Log.e("BaseDataSource", "\n ------------------ Start ------------------\n" +
                "----------------")

        Log.e("BaseDataSource ", where)

        Log.e("BaseDataSource ", "\n ------------------ End ------------------\n" +
                "----------------")


    }


}
