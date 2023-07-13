package com.example.studying_graphql.data.useCase

import com.example.studying_graphql.data.network.response.CountryDetailsResponse
import com.example.studying_graphql.data.network.wrapper.ResultWrapper
import com.example.studying_graphql.data.repository.CountryRepository

class GetCountryUseCase(private val repository: CountryRepository) {
    suspend fun execute(code: String): ResultWrapper<CountryDetailsResponse> {
        return repository.getCountry(code)
    }
}