package com.example.softwarelabassignment.model.response.signup

data class SignUpResponse(
    val account_preference: AccountPreference,
    val is_verified: Boolean,
    val message: String,
    val notification_settings: NotificationSettings,
    val success: Boolean,
    val token: String,
    val user: User
)