package com.example.softwarelabassignment.ui.fragment

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.adaptor.DropDownAdaptor
import com.example.softwarelabassignment.databinding.FragmentSignUpStep2Binding
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingActivity
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingViewModel
import com.example.softwarelabassignment.utils.ConstantData


class SignUpStep2Fragment : Fragment(R.layout.fragment_sign_up_step2) {

    private lateinit var binding:FragmentSignUpStep2Binding
    private lateinit var viewModel:OnBoardingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpStep2Binding.bind(view)
        viewModel = (activity as OnBoardingActivity).viewModel
        setUpUiBtn()
    }

    private fun checkForEmpty():Boolean {
         if(binding.businessNameTextInput.text.isNullOrBlank()) {
             showToast("Business name")
             return false
         }
        if(binding.informentTextInput.text.isNullOrBlank()) {
            showToast("Informants Name")
            return false
        }

        if(binding.streetAddressTextInput.text.isNullOrBlank()) {
            showToast("Address")
            return false
        }

        if(binding.cityEditTextInput.text.isNullOrBlank()) {
            showToast("City")
            return false
        }


        if(binding.zipcodeEditTextInput.text.isNullOrBlank()) {
            showToast("Zipcode")
            return false
        }

        if(binding.stateEditTextInput.text.isNullOrBlank()) {
            showToast("State ")
            return false
        }

        return true

    }

    private fun showToast(emptyPart: String) {
        Toast.makeText(requireContext(), "$emptyPart is empty fill it and try", Toast.LENGTH_SHORT)
            .show()
    }

    private fun setUpUiBtn() {
          binding.btGoBack.setOnClickListener {
                 activity?.onBackPressed()
          }

        binding.btContinue.setOnClickListener {
               if(checkForEmpty()) {
                   viewModel.signUpStep2(
                       binding.businessNameTextInput.text.toString(),
                       binding.informentTextInput.text.toString(),
                       binding.streetAddressTextInput.text.toString(),
                       binding.cityEditTextInput.text.toString(),
                       binding.stateEditTextInput.text.toString(),
                       binding.zipcodeEditTextInput.text.toString(),

                       )
                   findNavController().navigate(SignUpStep2FragmentDirections.actionSignUpStep2FragmentToSignUpVerificationFragment())
               }
        }
        binding.stateEditTextCL.setOnClickListener {
            // show list of option to select state from
            showDropDown(requireContext(),it)

        }


    }

    private fun showDropDown(context: Context, anchorView: View) {
        val list = ConstantData.getStateList()

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: View = inflater.inflate(R.layout.drop_down_list, null)
        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )
        popupWindow.isOutsideTouchable = true
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Set up RecyclerView to display the icons
        val recyclerView = popupView.findViewById<RecyclerView>(R.id.recyclerView)
        // create a new adaptor with click listener
        val adapter = DropDownAdaptor()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.differ.submitList(list)
        adapter.setOnItemClickListener { showActionItems, _ ->
            binding.stateEditTextInput.setText(showActionItems.name)
            popupWindow.dismiss()
        }
        // Show the popup above the anchor view

        // Show the popup above the anchor view
        val location = IntArray(2)
        anchorView.getLocationOnScreen(location)
        val x = location[0]
        val y = location[1] - popupView.height
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, x, y)

    }


}