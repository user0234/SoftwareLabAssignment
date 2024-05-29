package com.example.softwarelabassignment.model.response

data class VerifyOtpResponse(
    val message: String,
    val success: String,
    val token: String
)