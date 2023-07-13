package com.example.studying_graphql.data.network.response

import com.example.studying_graphql.domain.CountryDetailsPresentation

data class CountryDetailsResponse(
    val code: String,
    val name: String,
    val capital: String,
    val emoji: String,
    val currency: String,
    val languages: List<LanguageResponse>,
    val continent: ContinentResponse
) {
    fun toCountryDetailsPresentation() = CountryDetailsPresentation(
        code = code,
        name = name,
        capital = capital,
        emoji = emoji,
        currency = currency,
        languages = languages.first().name,
        continent = continent.name
    )
}
