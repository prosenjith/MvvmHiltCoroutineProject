package com.example.mvvmhiltcoroutineproject.data.network

import android.content.Context
import com.example.mvvmhiltcoroutineproject.R
import com.google.gson.Gson
import okhttp3.ResponseBody
import java.lang.reflect.Type

object ErrorHandler {
    fun parseIOException(appContext: Context): RequestException {
        return if (!ConnectivityHelper.isConnectedToInternet(appContext)) {
            RequestException(message = appContext.getString(R.string.msg_no_connection))
        } else {
            RequestException(message = appContext.getString(R.string.msg_unknown_exception))
        }
    }

    fun parseRequestException(
        appContext: Context,
        status: String,
        errorBody: ResponseBody? = null,
        message: String? = null
    ): RequestException {
        errorBody?.let { body ->
            val apiError: ApiError? = convertToType(ApiError::class.java, body)
            apiError?.let { error ->
                return RequestException().apply {
                    this.message = error.message ?: error.error?.message
                            ?: appContext.getString(R.string.msg_failed_try_again)
                    this.status = status
                    this.statusCode = 200
                }
            }
        }
        message?.let { msg ->
            return RequestException(message = msg)
        }
        return RequestException(message = appContext.getString(R.string.msg_failed_try_again))
    }

    private fun <T> convertToType(
        typeOfT: Type,
        errorBody: ResponseBody
    ): T? {
        val gson = Gson()
        return try {
            gson.fromJson<T>(errorBody.charStream(), typeOfT)
        } catch (ex: Exception) {
            null
        }
    }
}