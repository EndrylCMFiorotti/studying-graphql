package com.example.studying_graphql.data.network.response

import com.example.studying_graphql.domain.CountryPresentation

data class CountriesResponse(
    val results: List<CountryResponse>
) {
    fun toCountryPresentation(): List<CountryPresentation> = results.map {
        CountryPresentation(
            code = it.code,
            name = it.name,
            capital = it.capital,
            emoji = it.emoji
        )
    }
}