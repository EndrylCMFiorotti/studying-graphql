package com.example.studying_graphql.data.api

import com.example.studying_graphql.data.network.response.CountryDetailsResponse
import com.example.studying_graphql.data.network.response.CountryResponse

interface CountryClient {
    suspend fun getCountries(): List<CountryResponse>
    suspend fun getCountry(code: String): CountryDetailsResponse
}