package com.example.softwarelabassignment.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.databinding.FragmentSignUpHoursBinding


class SignUpHoursFragment : Fragment(R.layout.fragment_sign_up_hours) {


    private lateinit var binding:FragmentSignUpHoursBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpHoursBinding.bind(view)


    }


}