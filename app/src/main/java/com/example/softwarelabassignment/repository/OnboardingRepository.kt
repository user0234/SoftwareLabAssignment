package com.example.softwarelabassignment.repository

import android.util.ArrayMap
import android.util.Log
import com.example.softwarelabassignment.model.ForgotPasswordDataItem
import com.example.softwarelabassignment.model.LoginDataItem
import com.example.softwarelabassignment.model.ResetPassWordDataItem
import com.example.softwarelabassignment.model.SignUpDataItem
import com.example.softwarelabassignment.model.VerifyOtpDataItem
import com.example.softwarelabassignment.model.response.ForgotPasswordResponse
import com.example.softwarelabassignment.model.response.ResetPasswordResponse
import com.example.softwarelabassignment.model.response.VerifyOtpResponse
import com.example.softwarelabassignment.model.response.login.LoginResponse
import com.example.softwarelabassignment.model.response.signup.SignUpResponse
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response


class OnboardingRepository() {

    suspend fun login(loginData: LoginDataItem): Response<LoginResponse> {

        val gson = Gson()
        val jsonString = gson.toJson(loginData)
        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            jsonString
        )
        return RetrofitInstance.api.loginApi(body)

    }

    suspend fun signUp(signUpData: SignUpDataItem): Response<SignUpResponse> {

        val gson = Gson()
        val jsonString = gson.toJson(signUpData)

        val body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            jsonString
        )
        return RetrofitInstance.api.signUpApi(body)

    }

    suspend fun forgotPassword(forgotPasswordData: ForgotPasswordDataItem): Response<ForgotPasswordResponse> {
        val forgotPassword = forgotPasswordData.toString()

        return RetrofitInstance.api.forgotPasswordApi(forgotPassword)

    }

    suspend fun verifyOtp(verifyOtpData: VerifyOtpDataItem): Response<VerifyOtpResponse> {
        val verifyOtp = verifyOtpData.toString()

        return RetrofitInstance.api.verifyOtpApi(verifyOtp)

    }

    suspend fun resetPassword(resetPasswordData: ResetPassWordDataItem): Response<ResetPasswordResponse> {
        val resetPassword = resetPasswordData.toString()

        return RetrofitInstance.api.resetPasswordApi(resetPassword)

    }


}