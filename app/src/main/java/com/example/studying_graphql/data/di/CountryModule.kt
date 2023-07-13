package com.example.studying_graphql.data.di

import com.apollographql.apollo3.ApolloClient
import com.example.studying_graphql.data.network.interceptor.LoggingInterceptor
import com.example.studying_graphql.data.repository.CountryRepository
import com.example.studying_graphql.data.useCase.GetCountriesUseCase
import com.example.studying_graphql.data.useCase.GetCountryUseCase
import com.example.studying_graphql.presenter.viewModel.CountryViewModel
import okhttp3.Interceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val COUNTRY_INTERCEPTOR_KEY = "country_interceptor"
private const val COUNTRY_GRAPHQL_KEY = "country_graphql"

val module = module {
    factory<Interceptor>(named(COUNTRY_INTERCEPTOR_KEY)) { LoggingInterceptor() }

    single(named(COUNTRY_GRAPHQL_KEY)) {
        ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    factory { CountryRepository(get(named(COUNTRY_GRAPHQL_KEY))) }
    factory { GetCountryUseCase(get()) }
    factory { GetCountriesUseCase(get()) }
    viewModel { CountryViewModel(get(), get()) }
}