package com.example.studying_graphql.data.network.handler

import com.apollographql.apollo3.exception.ApolloHttpException
import com.example.studying_graphql.data.network.exceptions.ClientException
import com.example.studying_graphql.data.network.exceptions.ServerException
import com.example.studying_graphql.data.network.exceptions.UnknownResponseException
import com.example.studying_graphql.data.network.wrapper.ResultWrapper

suspend fun <T> requestHandler(function: suspend () -> T): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(function())
    } catch (e: ApolloHttpException) {
        ResultWrapper.Error(
            when (e.statusCode) {
                in 400..499 -> ClientException(code = e.statusCode, message = e.message)
                in 500..599 -> ServerException(code = e.statusCode, message = e.message)
                else -> UnknownResponseException(code = e.statusCode, message = e.message)
            }
        )
    }
}
