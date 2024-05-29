package com.example.softwarelabassignment.ui.fragment

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.databinding.FragmentVerifyOtpBinding
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingActivity
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingViewModel
import com.example.softwarelabassignment.utils.Resource


class VerifyOtpFragment : Fragment(R.layout.fragment_verify_otp) {

    private lateinit var binding: FragmentVerifyOtpBinding
    private lateinit var viewModel: OnBoardingViewModel
    private var phoneNumber = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentVerifyOtpBinding.bind(view)
        viewModel = (activity as OnBoardingActivity).viewModel
        phoneNumber = savedInstanceState?.getString("phoneNumbers").toString()

        binding.btLoginText.setOnClickListener {
            // navigate to login with clear the whole stack

        }
        binding.btResendCode.setOnClickListener {
            viewModel.forgotPassword(phoneNumber)
        }

        binding.btSubmit.setOnClickListener {
            val firstChar = binding.otpEditText1.text.toString()
            val secondChar = binding.otpEditText2.text.toString()
            val thirdChar = binding.otpEditText3.text.toString()
            val forthChar = binding.otpEditText4.text.toString()
            val fifthChar = binding.otpEditText5.text.toString()
            if (firstChar.length == 1 && secondChar.length == 1 && thirdChar.length == 1 && forthChar.length == 1 && fifthChar.length == 1) {

                val otp = "${firstChar}${secondChar}${thirdChar}${forthChar}${fifthChar}"

                viewModel.verifyOtp(otp)

            }

        }


        viewModel.verifyOtpResponse.observe(viewLifecycleOwner){
            when (it) {
                is Resource.Success -> {
                    if (it.data != null && it.data.success=="true") {
                        val bundle = Bundle()
                        bundle.putString("token",it.data.token)
                        findNavController().navigate(R.id.action_forgetPasswordFragment_to_verifyOtpFragment,bundle)
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






        viewModel.forgotPasswordResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    if (it.data != null&&it.data.success == "true") {

                        Toast.makeText(this.context, it.data.message, Toast.LENGTH_SHORT).show()
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


        // go to next edit text

        binding.otpEditText1.setOnClickListener {
            if (binding.otpEditText1.text.toString().length == 1) {
                binding.otpEditText2.requestFocus()
            }
        }
        binding.otpEditText2.setOnClickListener {
            if (binding.otpEditText2.text.toString().length == 1) {
                binding.otpEditText3.requestFocus()
            }
        }
        binding.otpEditText3.setOnClickListener {
            if (binding.otpEditText3.text.toString().length == 1) {
                binding.otpEditText4.requestFocus()
            }
        }
        binding.otpEditText4.setOnClickListener {
            if (binding.otpEditText4.text.toString().length == 1) {
                binding.otpEditText5.requestFocus()
            }
        }
        binding.otpEditText5.setOnClickListener {
            if (binding.otpEditText5.text.toString().length == 1) {
                binding.otpEditText5.clearFocus()
            }
        }

        // move to previous item

        binding.otpEditText2.setOnKeyListener { _, keyCode, _ ->

            if (keyCode == KeyEvent.KEYCODE_DEL) {
                binding.otpEditText1.requestFocus()
                binding.otpEditText2.setText("")
            }
            return@setOnKeyListener true
        }
        binding.otpEditText3.setOnKeyListener { _, keyCode, _ ->

            if (keyCode == KeyEvent.KEYCODE_DEL) {
                binding.otpEditText2.requestFocus()
                binding.otpEditText3.setText("")
            }
            return@setOnKeyListener true
        }
        binding.otpEditText4.setOnKeyListener { _, keyCode, _ ->

            if (keyCode == KeyEvent.KEYCODE_DEL) {
                binding.otpEditText3.requestFocus()
                binding.otpEditText4.setText("")
            }
            return@setOnKeyListener true
        }
        binding.otpEditText5.setOnKeyListener { _, keyCode, _ ->

            if (keyCode == KeyEvent.KEYCODE_DEL) {
                binding.otpEditText4.requestFocus()
                binding.otpEditText5.setText("")
            }
            return@setOnKeyListener true
        }

    }


}