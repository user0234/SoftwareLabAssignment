package com.example.softwarelabassignment.model.response.signup

data class AccountPreference(
    val currency: String,
    val locale: String,
    val time_zone: String
)