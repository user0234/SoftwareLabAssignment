package com.example.softwarelabassignment.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.adaptors.VerificationListAdaptor
import com.example.softwarelabassignment.databinding.FragmentSignUpVerificationBinding
import com.example.softwarelabassignment.model.VerificationDataItem
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingActivity
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingViewModel


class SignUpVerificationFragment : Fragment(R.layout.fragment_sign_up_verification) {


    private lateinit var binding:FragmentSignUpVerificationBinding
    private lateinit var verificationAdaptor: VerificationListAdaptor

    private lateinit var viewModel:OnBoardingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding  = FragmentSignUpVerificationBinding.bind(view)
        viewModel = (activity as OnBoardingActivity).viewModel
        setUpRecyclerView()

        setUpUiBtn()
    }

    private fun setUpRecyclerView() {
        verificationAdaptor = VerificationListAdaptor()
        binding.attachedRv.apply {
             adapter = verificationAdaptor
             layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        }

    }

    private fun setUpUiBtn() {
        val result: OnBoardingViewModel.ImageResponseDataItem =
            OnBoardingViewModel.ImageResponseDataItem("name") {

                verificationAdaptor.differ.submitList(listOf(VerificationDataItem(it)))

            }
          binding.btCameraCl.setOnClickListener {
              viewModel.startGetImage(result)

          }

        binding.btContinue.setOnClickListener {
            val items = verificationAdaptor.differ.currentList.map {
                it.name
            }
            if(items.isNotEmpty()){
                viewModel.signUpStep3(items.toTypedArray())
                findNavController().navigate(SignUpVerificationFragmentDirections.actionSignUpVerificationFragmentToSignUpHoursFragment())
            }

        }
        binding.btGoBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

}