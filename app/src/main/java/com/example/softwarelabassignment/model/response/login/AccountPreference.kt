package com.example.softwarelabassignment.model.response.login

data class AccountPreference(
    val currency: String,
    val locale: String,
    val time_zone: String
)