package com.example.studying_graphql.data.useCase

import com.example.studying_graphql.data.network.response.CountriesResponse
import com.example.studying_graphql.data.network.wrapper.ResultWrapper
import com.example.studying_graphql.data.repository.CountryRepository

class GetCountriesUseCase(private val repository: CountryRepository) {
    suspend fun execute(): ResultWrapper<CountriesResponse> {
        return repository.getCountries()
    }
}