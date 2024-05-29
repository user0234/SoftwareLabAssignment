package com.example.softwarelabassignment.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.databinding.FragmentForgetPasswordBinding
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingActivity
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingViewModel
import com.example.softwarelabassignment.utils.Resource
import com.example.softwarelabassignment.utils.SharedPrefUtil


class ForgetPasswordFragment : Fragment(R.layout.fragment_forget_password) {


    private lateinit var binding:FragmentForgetPasswordBinding
    private lateinit var viewModel: OnBoardingViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgetPasswordBinding.bind(view)
        viewModel = (activity as OnBoardingActivity).viewModel


        binding.btLoginText.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.btSendCode.setOnClickListener {
           val phoneNumberValue = binding.textInputNumber.text
            if(!phoneNumberValue.isNullOrEmpty()){
                if(phoneNumberValue.toString().length==10){
                      viewModel.forgotPassword("+91$phoneNumberValue")
                }else{
                    Toast.makeText(this.context, "please enter correct number", Toast.LENGTH_SHORT).show()

                }

            }else{
                Toast.makeText(this.context, "please enter phone number", Toast.LENGTH_SHORT).show()

            }
        }

        viewModel.forgotPasswordResponse.observe(viewLifecycleOwner){
            when(it) {
                is Resource.Success -> {
                    if (it.data != null) {
                        Toast.makeText(this.context, it.data.message, Toast.LENGTH_SHORT).show()
                        if (it.data.success == "true") {
                            // otp sent start the otp verification fragment

                            findNavController().navigate(ForgetPasswordFragmentDirections.actionForgetPasswordFragmentToVerifyOtpFragment())
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