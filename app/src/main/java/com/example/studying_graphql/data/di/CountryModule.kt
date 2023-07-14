package com.example.studying_graphql.data.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.studying_graphql.data.network.interceptor.LoggingInterceptor
import com.example.studying_graphql.data.repository.CountryRepository
import com.example.studying_graphql.data.useCase.GetCountriesUseCase
import com.example.studying_graphql.data.useCase.GetCountryUseCase
import com.example.studying_graphql.presenter.viewModel.CountryViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    single { LoggingInterceptor() }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<LoggingInterceptor>())
            .build()
    }

    single {
        ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .okHttpClient(get())
            .build()
    }

    factory { CountryRepository(get()) }
    factory { GetCountryUseCase(get()) }
    factory { GetCountriesUseCase(get()) }
    viewModel { CountryViewModel(get(), get()) }
}