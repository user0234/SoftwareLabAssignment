package com.example.softwarelabassignment.model.response.login

data class User(
    val avatar: String,
    val device_token: String,
    val email: String,
    val full_name: String,
    val id: String,
    val social_id: String,
    val type: String
)