package com.example.studying_graphql.data.network.exceptions

data class ClientException(
    override val message: String?,
    val code: Int
) : Exception(message)