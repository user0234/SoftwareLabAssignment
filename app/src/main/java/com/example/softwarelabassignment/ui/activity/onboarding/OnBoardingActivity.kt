package com.example.softwarelabassignment.ui.activity.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.databinding.ActivityOnBoardingBinding
import com.example.softwarelabassignment.repository.OnboardingRepository
import com.example.softwarelabassignment.utils.observeEvent

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var navController: NavController
    lateinit var viewModel: OnBoardingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = OnboardingRepository()

        val viewModelProviderFactory = onBoardingViewModelFactory(application, repository)

        viewModel =
            ViewModelProvider(this, viewModelProviderFactory)[OnBoardingViewModel::class.java]

        handleEvents()


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.weatherNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController


    }

    private fun handleEvents() {

        var imageResponse: OnBoardingViewModel.ImageResponseDataItem? = null

        val openSingleDoc =
            registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->

                imageResponse?.response?.let { it(uri?.path ?: ("cards" + ".pdf")) }

            }



        viewModel.handleGetImageEvent.observeEvent(this) { value ->
            imageResponse = value

            openSingleDoc.launch(arrayOf("application/pdf"))

        }
    }
}