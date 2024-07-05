package com.ms.taskprac.network

sealed class ResponseStatus<out T> {
    data class Success<T>(val response: T) : ResponseStatus<T>()
    data class Error(val responseCode: Int): ResponseStatus<Nothing>()
}