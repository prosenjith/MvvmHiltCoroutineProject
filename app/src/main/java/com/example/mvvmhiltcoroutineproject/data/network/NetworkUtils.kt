package com.example.mvvmhiltcoroutineproject.data.network

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import retrofit2.Response

fun <T> Response<T>.onResponse(): T {
    if (isSuccessful) {
        return body() ?: throw ApiException(
            code().toString(),
            null,
            message()
        )
    } else {
        throw ApiException(code().toString(), errorBody(), message())
    }
}

fun <T> Flow<T>.onException(context:Context): Flow<T> {
    return catch {e->
        if (e is ApiException) throw ErrorHandler.parseRequestException(context, e.status, e.errorBody, e.message)
        else throw ErrorHandler.parseIOException(context)
    }
}