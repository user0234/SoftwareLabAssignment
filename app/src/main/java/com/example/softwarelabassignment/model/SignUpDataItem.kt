package com.example.softwarelabassignment.model

data class SignUpDataItem(
    var address: String="",
    var business_hours: BusinessHours? = null,
    var business_name: String="",
    var city: String="",
    var device_token: String="0imfnc8mVLWwsAawjYr4Rx-Af50DDqtlx",
    var email: String="",
    var full_name: String="",
    var informal_name: String="",
    var password: String="",
    var phone: String="",
    var registration_proof: String="",
    var role: String="farmer",
    var social_id: String="0imfnc8mVLWwsAawjYr4Rx-Af50DDqtlx",
    var state: String="",
    var type: String="email",
    var zip_code: String? = null
)