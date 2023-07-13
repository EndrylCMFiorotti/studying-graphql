package com.example.studying_graphql.data.network.wrapper

import java.lang.Exception

sealed class ResultWrapper<out T> {
    data class Success<out T>(val content: T) : ResultWrapper<T>()
    data class Error(val error: Exception) : ResultWrapper<Nothing>()
}