package com.example.composecleanarchitecture.data.network

import com.example.composecleanarchitecture.models.BaseResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


sealed class AppNetworkState<out T> {
    data object Loading : AppNetworkState<Nothing>()

    data class Error(
        var exception: NetworkErrorExceptions,
        var errorBody: BaseResponse? = null,
        var unauthorized: Boolean = false
    ) : AppNetworkState<Nothing>()

    data class Data<T>(var data: T) : AppNetworkState<T>()
}

open class NetworkErrorExceptions(
    val errorCode: Int = -1,
    val errorMessageRes: Int? = null,
    val errorMessage: String? = "Something went wrong!",
    val errorBody: BaseResponse? = null,
    val unauthorized: Boolean = false
) : Exception() {
    override val message: String?
        get() = errorMessage

    companion object {
        fun parseException(exception: HttpException): NetworkErrorExceptions {
            return try {
                val errorBody: BaseResponse? =
                    exception.response()?.errorBody()
                        ?.convertBody()

                NetworkErrorExceptions(
                    errorCode = exception.code(),
                    errorMessage = errorBody?.status_message ?: "request failed",
                    errorBody = errorBody,
                    unauthorized = exception.code() == 401 // unauthorized true if 401
                )
            } catch (_: Exception) {
                NetworkErrorExceptions(
                    errorCode = exception.code(),
                    errorMessage = "unexpected error!!",
                )
            }
        }
    }
}

fun Exception.resolveError(): AppNetworkState.Error {
    when (this) {
        is SocketTimeoutException -> {
            val exception = NetworkErrorExceptions(errorMessage = "connection timeout!")
            return AppNetworkState.Error(
                exception = exception,
                errorBody = exception.errorBody,
                unauthorized = exception.unauthorized
            )
        }
        is ConnectException -> {
            val exception = NetworkErrorExceptions(errorMessage = "internet connection failed!")
            return AppNetworkState.Error(
                exception = exception,
                errorBody = exception.errorBody,
                unauthorized = exception.unauthorized
            )
        }
        is UnknownHostException -> {
            val exception = NetworkErrorExceptions(errorMessage = "host not found!")
            return AppNetworkState.Error(
                exception = exception,
                errorBody = exception.errorBody,
                unauthorized = exception.unauthorized
            )
        }
        is HttpException -> {
            val exception = NetworkErrorExceptions.parseException(this)
            return AppNetworkState.Error(
                exception = exception,
                errorBody = exception.errorBody,
                unauthorized = exception.unauthorized
            )
        }
    }

    return AppNetworkState.Error(
        exception = NetworkErrorExceptions(),
        errorBody = null,
        unauthorized = false
    )
}

inline fun <reified T : Any> Response<ResponseBody>.convertData(): T {
    val body = if (this.isSuccessful) {
        this.body()?.string()
    } else throw HttpException(this)

    return GsonBuilder().serializeNulls().create().fromJson(
        body,
        T::class.java
    )
}

inline fun <reified T : Any> ResponseBody.convertBody(): T {
    return GsonBuilder().serializeNulls().create().fromJson(
        this.string(),
        T::class.java
    )
}

fun apolloResponseToRetrofitResponse(apolloResponse: Any): Response<ResponseBody> {

    val jsonString = Gson().toJson(apolloResponse).toString()
    val mediaType = "application/json".toMediaTypeOrNull()
    val responseBody = ResponseBody.create(mediaType, jsonString)
    return Response.success(responseBody)
}
