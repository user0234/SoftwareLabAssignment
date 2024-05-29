package com.example.softwarelabassignment.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.databinding.FragmentSignUpConfirmationBinding


class SignUpConfirmationFragment : Fragment(R.layout.fragment_sign_up_confirmation) {

    private lateinit var binding:FragmentSignUpConfirmationBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignUpConfirmationBinding.bind(view)

    }


    companion object {

        @JvmStatic
        fun newInstance() =
            SignUpConfirmationFragment()
            }
    }
