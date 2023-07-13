package com.example.studying_graphql.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studying_graphql.data.network.wrapper.ResultWrapper
import com.example.studying_graphql.data.useCase.GetCountriesUseCase
import com.example.studying_graphql.data.useCase.GetCountryUseCase
import com.example.studying_graphql.domain.CountryDetailsPresentation
import com.example.studying_graphql.domain.CountryPresentation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryViewModel(
    private val countryUseCase: GetCountryUseCase,
    private val countriesUseCase: GetCountriesUseCase
) : ViewModel() {
    private val _countries = MutableLiveData<List<CountryPresentation>>()
    val countries: LiveData<List<CountryPresentation>> = _countries

    private val _country = MutableLiveData<CountryDetailsPresentation>()
    val country: LiveData<CountryDetailsPresentation> = _country

    private val _empty = MutableLiveData<Unit>()
    val empty: LiveData<Unit> = _empty

    private val _error = MutableLiveData<Exception>()
    val error: LiveData<Exception> = _error

    fun getCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = countriesUseCase.execute()) {
                is ResultWrapper.Success -> {
                    response.content.toCountryPresentation().let {
                        when {
                            it.isEmpty() -> _empty.postValue(Unit)
                            else -> _countries.postValue(it)
                        }
                    }
                }

                is ResultWrapper.Error -> _error.postValue(response.error)
            }
        }
    }

    fun getCountry(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = countryUseCase.execute(code)) {
                is ResultWrapper.Success -> {
                    response.content.toCountryDetailsPresentation().let {
                        _country.postValue(it)
                    }
                }

                is ResultWrapper.Error -> _error.postValue(response.error)
            }
        }
    }
}