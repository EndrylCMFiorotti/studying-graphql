package com.example.studying_graphql.data.network.ext

import com.example.CountryQuery
import com.example.studying_graphql.data.network.response.ContinentResponse
import com.example.studying_graphql.data.network.response.CountryDetailsResponse
import com.example.studying_graphql.data.network.response.LanguageResponse

fun CountryQuery.Country.toResponse() = CountryDetailsResponse(
    code = code,
    name = name,
    capital = capital ?: "No capital",
    emoji = emoji,
    currency = currency ?: "No currency",
    languages = languages.map { it.toResponse() },
    continent = continent.toResponse()
)

fun CountryQuery.Language.toResponse() = LanguageResponse(
    name = name
)

fun CountryQuery.Continent.toResponse() = ContinentResponse(
    name = name
)