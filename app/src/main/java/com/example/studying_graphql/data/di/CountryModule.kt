package com.example.studying_graphql.data.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.studying_graphql.data.network.interceptor.LoggingInterceptor
import com.example.studying_graphql.data.repository.CountryRepository
import com.example.studying_graphql.data.useCase.GetCountriesUseCase
import com.example.studying_graphql.data.useCase.GetCountryUseCase
import com.example.studying_graphql.presenter.viewModel.CountryViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val COUNTRY_INTERCEPTOR_KEY = "country_interceptor"
private const val COUNTRY_GRAPHQL_KEY = "country_graphql"
private const val COUNTRY_OKHTTP_KEY = "country_okhttp"

val module = module {
    factory<Interceptor>(named(COUNTRY_INTERCEPTOR_KEY)) { LoggingInterceptor() }

    single(named(COUNTRY_OKHTTP_KEY)) {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(named(COUNTRY_INTERCEPTOR_KEY)))
            .build()
    }

    single(named(COUNTRY_GRAPHQL_KEY)) {
        ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .okHttpClient(get(named(COUNTRY_OKHTTP_KEY)))
            .build()
    }

    factory { CountryRepository(get(named(COUNTRY_GRAPHQL_KEY))) }

    factory { GetCountryUseCase(get()) }

    factory { GetCountriesUseCase(get()) }

    viewModel { CountryViewModel(get(), get()) }
}