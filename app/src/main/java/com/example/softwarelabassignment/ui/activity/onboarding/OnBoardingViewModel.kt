package com.example.softwarelabassignment.ui.activity.onboarding

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.softwarelabassignment.model.BusinessHours
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
import com.example.softwarelabassignment.repository.OnboardingRepository
import com.example.softwarelabassignment.utils.AppApplicationClass
import com.example.softwarelabassignment.utils.Event
import com.example.softwarelabassignment.utils.Resource
import com.example.softwarelabassignment.utils.send
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class OnBoardingViewModel(
    app: Application,
    private val onBoardingRepository: OnboardingRepository
) : AndroidViewModel(app) {

    private val loginData: LoginDataItem = LoginDataItem()


    //  login
    val loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()


    fun loginUser(email: String, password: String) {
        val newData = loginData.apply {
            this.email = email
            this.password = password
        }

        viewModelScope.launch(Dispatchers.IO) {
            safeLoginUser(newData)
        }


    }

    private suspend fun safeLoginUser(loginInfo: LoginDataItem) {
        loginResponse.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = onBoardingRepository.login(loginInfo)
                loginResponse.postValue(handleLoginResponse(response))
            } else {
                loginResponse.postValue(Resource.Error("Check your internet"))
            }

        } catch (t: Throwable) {
            Log.i("ApiError","error - ${t.message} cause -${t.cause}")
             t.printStackTrace()
            loginResponse.postValue(Resource.Error("an Error has occurred"))
        }

    }

    private fun handleLoginResponse(response: Response<LoginResponse>): Resource<LoginResponse> {
        Log.i("apiResponse"," sucess - ${response.isSuccessful} login response body ${response.body()} ,login response raw - ${response.raw()}")

        if (response.isSuccessful) {
            if (response.body() != null && response.body()?.token != null) {
                return Resource.Success(response.body())
            }
        }
        return Resource.Error("error occurred - "+response.message())
    }


    // signUp

    val signUpDataItem = SignUpDataItem()

    val signUpResponse: MutableLiveData<Resource<SignUpResponse>> = MutableLiveData()


    fun signUpStep1(name: String, email: String, phoneNumber: String, password: String) {

        signUpDataItem.apply {
            this.full_name = name
            this.email = email
            this.phone = phoneNumber
            this.password = password
        }

    }

    fun signUpStep2(
        businessName: String,
        informalName: String,
        streetAddress: String,
        city: String,
        state: String,
        zipCode: String
    ) {

        signUpDataItem.apply {
            this.business_name = businessName
            this.informal_name = informalName
            this.address = streetAddress
            this.city = city
            this.state = state
            this.zip_code = zipCode
        }

    }

    fun signUpStep3(
        fileNames:Array<String>
    ) {
        val fileName = fileNames.toString()
        signUpDataItem.apply {
            this.registration_proof = fileName
        }

    }

    fun signUpStep4(businessHours: BusinessHours) {


        signUpDataItem.apply {
            this.business_hours = businessHours
        }

        viewModelScope.launch(Dispatchers.IO) {
            safeSignUp(signUpDataItem)
        }

    }

    private suspend fun safeSignUp(signUpInfo: SignUpDataItem) {
        signUpResponse.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = onBoardingRepository.signUp(signUpInfo)
                signUpResponse.postValue(handleSignUpResponse(response))
            } else {
                signUpResponse.postValue(Resource.Error("Check your internet"))
            }

        } catch (t: Throwable) {
            signUpResponse.postValue(Resource.Error("an Error has occurred"))
        }

    }

    private fun handleSignUpResponse(response: Response<SignUpResponse>): Resource<SignUpResponse> {
        if (response.isSuccessful) {
            if (response.body() != null && response.body()?.token != null) {
                return Resource.Success(response.body())
            }
        }
        return Resource.Error(response.message())
    }

    ///  forgotPassword

    private val forgotPassword: ForgotPasswordDataItem = ForgotPasswordDataItem()

    val forgotPasswordResponse: MutableLiveData<Resource<ForgotPasswordResponse>> =
        MutableLiveData()


    fun forgotPassword(phoneNumber: String) {
        val newData = forgotPassword.apply {
            this.mobile = phoneNumber
        }

        viewModelScope.launch(Dispatchers.IO) {
            safeForgotPassword(newData)
        }


    }

    private suspend fun safeForgotPassword(forgotPasswordInfo: ForgotPasswordDataItem) {
        forgotPasswordResponse.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = onBoardingRepository.forgotPassword(forgotPasswordInfo)
                forgotPasswordResponse.postValue(handleForgotPasswordResponse(response))
            } else {
                forgotPasswordResponse.postValue(Resource.Error("Check your internet"))
            }

        } catch (t: Throwable) {
            forgotPasswordResponse.postValue(Resource.Error("an Error has occurred"))
        }

    }

    private fun handleForgotPasswordResponse(response: Response<ForgotPasswordResponse>): Resource<ForgotPasswordResponse> {
        if (response.isSuccessful) {
            if (response.body() != null && response.body()?.success != null) {
                return Resource.Success(response.body())
            }
        }
        return Resource.Error(response.message())
    }


    ///  verifyOtp

    private val verifyOtp: VerifyOtpDataItem = VerifyOtpDataItem()

    val verifyOtpResponse: MutableLiveData<Resource<VerifyOtpResponse>> = MutableLiveData()


    fun verifyOtp(otp: String) {
        val newData = verifyOtp.apply {
            this.otp = otp
        }

        viewModelScope.launch(Dispatchers.IO) {
            safeVerifyOtp(newData)
        }


    }

    private suspend fun safeVerifyOtp(verifyOtpInfo: VerifyOtpDataItem) {
        verifyOtpResponse.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = onBoardingRepository.verifyOtp(verifyOtpInfo)
                verifyOtpResponse.postValue(handleVerifyOtpResponse(response))
            } else {
                verifyOtpResponse.postValue(Resource.Error("Check your internet"))
            }

        } catch (t: Throwable) {
            verifyOtpResponse.postValue(Resource.Error("an Error has occurred"))
        }

    }

    private fun handleVerifyOtpResponse(response: Response<VerifyOtpResponse>): Resource<VerifyOtpResponse> {
        if (response.isSuccessful) {
            if (response.body() != null && response.body()?.success != null) {
                return Resource.Success(response.body())
            }
        }
        return Resource.Error(response.message())
    }


    ///  resetPassword

    private val resetPassword: ResetPassWordDataItem = ResetPassWordDataItem()

    val resetPasswordResponse: MutableLiveData<Resource<ResetPasswordResponse>> = MutableLiveData()


    fun resetPassword(password: String, cPassword: String, token: String) {
        val newData = resetPassword.apply {
            this.password = password
            this.cpassword = cPassword
            this.token = token
        }

        viewModelScope.launch(Dispatchers.IO) {
            safeResetPassword(newData)
        }


    }

    private suspend fun safeResetPassword(resetPasswordInfo: ResetPassWordDataItem) {
        resetPasswordResponse.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = onBoardingRepository.resetPassword(resetPasswordInfo)
                resetPasswordResponse.postValue(handleResetPasswordResponse(response))
            } else {
                resetPasswordResponse.postValue(Resource.Error("Check your internet"))
            }

        } catch (t: Throwable) {
            resetPasswordResponse.postValue(Resource.Error("an Error has occurred"))
        }

    }

    private fun handleResetPasswordResponse(response: Response<ResetPasswordResponse>): Resource<ResetPasswordResponse> {
        if (response.isSuccessful) {
            if (response.body() != null && response.body()?.success != null) {
                return Resource.Success(response.body())
            }
        }
        return Resource.Error(response.message())
    }


    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<AppApplicationClass>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    // start image getting

    private val _promptGetImage = MutableLiveData<Event<ImageResponseDataItem>>()
    val handleGetImageEvent: LiveData<Event<ImageResponseDataItem>>
        get() = _promptGetImage


    fun startGetImage(request:ImageResponseDataItem) {
        _promptGetImage.send(request)
    }

    data class ImageResponseDataItem(
        var value:String,
        var response:(String) -> Unit
    )

}