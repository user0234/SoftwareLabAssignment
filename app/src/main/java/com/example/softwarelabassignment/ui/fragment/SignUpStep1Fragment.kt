package com.example.softwarelabassignment.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.databinding.FragmentSignUpStep1Binding
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingActivity
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingViewModel


class SignUpStep1Fragment : Fragment(R.layout.fragment_sign_up_step1) {


    private lateinit var binding: FragmentSignUpStep1Binding
    private lateinit var viewModel: OnBoardingViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpStep1Binding.bind(view)
        viewModel = (activity as OnBoardingActivity).viewModel
        setUpUi()
    }

    private fun setUpUi() {
        binding.btLogin.setOnClickListener {
            findNavController().navigate(SignUpStep1FragmentDirections.actionSignUpStep1Fragment2ToLoginFragment())
        }
        binding.btContinue.setOnClickListener {
            if (checkForBlanks()) {
                viewModel.signUpStep1(
                    binding.nameTextInput.text.toString(),
                    binding.emailTextInput.text.toString(),
                    binding.phoneTextInput.text.toString(),
                    binding.passwordEditTextInput.text.toString()
                )
                findNavController().navigate(SignUpStep1FragmentDirections.actionSignUpStep1Fragment2ToSignUpStep2Fragment())
            }

        }
    }

    private fun checkForBlanks(): Boolean {
        if (binding.nameTextInput.text.isNullOrBlank()) {
            showToast("Name")
            return false
        }
        if (binding.emailTextInput.text.isNullOrBlank()) {
            showToast("Email")
            return false
        }
        if (binding.phoneTextInput.text.isNullOrBlank()) {
            showToast("Phone")
            return false
        }
        if (binding.passwordEditTextInput.text.isNullOrBlank()) {
            showToast("Password")
            return false
        }
        if (binding.passwordEditTextInput.text == binding.passwordConfirmEditTextInput.text) {
            Toast.makeText(requireContext(), "Password don't match", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun showToast(emptyPart: String) {
        Toast.makeText(requireContext(), "$emptyPart is empty fill it and try", Toast.LENGTH_SHORT)
            .show()
    }


}