package com.example.softwarelabassignment.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.databinding.FragmentLoginBinding
import com.example.softwarelabassignment.ui.activity.HomeScreenActivity
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingActivity
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingViewModel
import com.example.softwarelabassignment.utils.Resource
import com.example.softwarelabassignment.utils.SharedPrefUtil.putDeviceLoggedInToken

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: OnBoardingViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        viewModel = (activity as OnBoardingActivity).viewModel
        // try logging in
        binding.btLogin.setOnClickListener {

            viewModel.loginUser(
                binding.textInputEmail.text.toString(),
                binding.textInputPassword.text.toString()
            )

        }

        binding.createNewSecondText.setOnClickListener {
            // go to signup fragment
            findNavController().navigate(R.id.action_loginFragment_to_signUpStep1Fragment2)
        }

        binding.textInputPassword.addTextChangedListener {
            if (it.isNullOrEmpty()) {
                binding.forgotBt.visibility = View.VISIBLE
            } else {
                binding.forgotBt.visibility = View.GONE
            }
        }

        binding.forgotBt.setOnClickListener {
            // go to forgot fragment
            findNavController().navigate(R.id.action_loginFragment_to_forgetPasswordFragment)

        }

        viewModel.loginResponse.observe(activity as OnBoardingActivity) {
            when (it) {
                is Resource.Success -> {
                    if (it.data != null) {
                        if (it.data.token != "") {
                            putDeviceLoggedInToken(this.requireContext(), it.data.token)
                            // view Model to start the main activity

                            startActivity(
                                Intent(
                                    requireActivity(),
                                    HomeScreenActivity::class.java
                                )
                            )
                            activity?.finish()

                        } else {
                            Toast.makeText(this.context, it.data.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(this.context, "${it.message}", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    Toast.makeText(this.context, "loading", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}
