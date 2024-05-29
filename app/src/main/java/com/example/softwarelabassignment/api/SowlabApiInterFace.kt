package com.example.softwarelabassignment.api

import com.example.softwarelabassignment.model.response.ForgotPasswordResponse
import com.example.softwarelabassignment.model.response.ResetPasswordResponse
import com.example.softwarelabassignment.model.response.VerifyOtpResponse
import com.example.softwarelabassignment.model.response.login.LoginResponse
import com.example.softwarelabassignment.model.response.signup.SignUpResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SowlabApiInterFace {

    @POST("user/login")
    suspend fun loginApi(
        @Body rawBody: RequestBody
    ): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("user/register")
    suspend fun signUpApi(
        @Body rawBody: String
    ): Response<SignUpResponse>

    @Headers("Content-Type: application/json")
    @POST("user/forgot-password")
    suspend fun forgotPasswordApi(
        @Body rawBody: String
    ): Response<ForgotPasswordResponse>

    @Headers("Content-Type: application/json")
    @POST("user/verify-otp")
    suspend fun verifyOtpApi(
        @Body rawBody: String
    ): Response<VerifyOtpResponse>

    @Headers("Content-Type: application/json")
    @POST("user/reset-password")
    suspend fun resetPasswordApi(
        @Body rawBody: String
    ): Response<ResetPasswordResponse>

}