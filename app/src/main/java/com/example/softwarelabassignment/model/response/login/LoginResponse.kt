package com.example.softwarelabassignment.model.response.login

data class LoginResponse(
    val account_preference: AccountPreference,
    val is_verified: Boolean,
    val message: String,
    val notification_settings: NotificationSettings,
    val success: Boolean,
    val token: String,
    val user: User
)