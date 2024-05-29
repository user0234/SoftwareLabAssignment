package com.example.softwarelabassignment.ui.activity.onboarding

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.softwarelabassignment.repository.HourItemRepository
import com.example.softwarelabassignment.repository.OnboardingRepository


class onBoardingViewModelFactory(
    val app: Application,
    private val onBoardingRepository: OnboardingRepository,
    private val hourItemRepository: HourItemRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnBoardingViewModel(app,onBoardingRepository,hourItemRepository) as T
    }
}