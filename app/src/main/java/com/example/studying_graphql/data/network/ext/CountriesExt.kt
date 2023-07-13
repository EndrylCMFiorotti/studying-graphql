package com.example.studying_graphql.data.network.ext

import com.example.CountriesQuery
import com.example.studying_graphql.data.network.response.CountriesResponse
import com.example.studying_graphql.data.network.response.CountryResponse

fun List<CountriesQuery.Country>.toResponse() = CountriesResponse(
    results = this.map {
        it.toResponse()
    }
)

fun CountriesQuery.Country.toResponse() = CountryResponse(
    code = code,
    name = name,
    capital = capital ?: "No capital",
    emoji = emoji
)