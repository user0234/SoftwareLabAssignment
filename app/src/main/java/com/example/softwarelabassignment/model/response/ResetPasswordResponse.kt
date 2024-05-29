package com.example.softwarelabassignment.model.response

data class ResetPasswordResponse(
    val is_verified: String,
    val message: String,
    val success: String
)