package com.example.softwarelabassignment.model

data class SignUpDataItem(
    var address: String="",
    var business_hours: BusinessHours? = null,
    var business_name: String="",
    var city: String="",
    var device_token: String="",
    var email: String="",
    var full_name: String="",
    var informal_name: String="",
    var password: String="",
    var phone: String="",
    var registration_proof: String="",
    var role: String="",
    var social_id: String="",
    var state: String="",
    var type: String="",
    var zip_code: String? = null
)