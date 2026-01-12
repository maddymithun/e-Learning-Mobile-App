package com.example.composecleanarchitecture.data.network

sealed class NetworkResponseState<T> {
    data class Success<T>(val data: T) : NetworkResponseState<T>()
    data class Error<T>(val message: String) : NetworkResponseState<T>()
    companion object {
        fun <T> success(data: T) = Success<T>(data)
        fun <T> error(message: String) = Error<T>(message)
    }
}
