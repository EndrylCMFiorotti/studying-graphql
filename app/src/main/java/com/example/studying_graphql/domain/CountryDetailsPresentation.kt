package com.example.studying_graphql.domain

data class CountryDetailsPresentation(
    val code: String,
    val name: String,
    val capital: String,
    val emoji: String,
    val currency: String,
    val languages: String,
    val continent: String
)