package com.example.softwarelabassignment.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.databinding.FragmentResetPasswordBinding


class ResetPasswordFragment : Fragment(R.layout.fragment_reset_password) {

  private lateinit var binding:FragmentResetPasswordBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResetPasswordBinding.bind(view)
    }

    companion object {
        @JvmStatic
        fun newInstance() {

        }
    }

}