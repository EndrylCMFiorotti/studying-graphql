package com.example.studying_graphql.data.repository

import com.apollographql.apollo3.ApolloClient
import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.studying_graphql.data.network.ext.toResponse
import com.example.studying_graphql.data.network.handler.requestHandler
import com.example.studying_graphql.data.network.response.CountriesResponse
import com.example.studying_graphql.data.network.response.CountryDetailsResponse
import com.example.studying_graphql.data.network.wrapper.ResultWrapper

class CountryRepository(private val apolloClient: ApolloClient) {

    suspend fun getCountries(): ResultWrapper<CountriesResponse> {
        return requestHandler {
            apolloClient
                .query(CountriesQuery())
                .execute()
                .data
                ?.countries
                ?.toResponse()!!
        }
    }

    suspend fun getCountry(code: String): ResultWrapper<CountryDetailsResponse> {
        return requestHandler {
            apolloClient
                .query(CountryQuery(code))
                .execute()
                .data
                ?.country
                ?.toResponse()!!
        }
    }
}