package com.example.softwarelabassignment.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.adaptor.onboarding.OnboardingListAdaptor
import com.example.softwarelabassignment.databinding.FragmentOnBoardingBinding
import com.example.softwarelabassignment.utils.ConstantData.getAllOnBoardingScreen
import com.example.softwarelabassignment.utils.SharedPrefUtil

class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {

     private lateinit var binding:FragmentOnBoardingBinding
     private lateinit var adaptor: OnboardingListAdaptor
    private var currentVisible: Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOnBoardingBinding.bind(view)
        setUpRecyclerView()
        val isNotFirst = SharedPrefUtil.getAppFirstTimeOpen(requireContext())
        if(isNotFirst){
            findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToLoginFragment())
        }
    }

   private fun setUpRecyclerView(){
       adaptor = OnboardingListAdaptor()

       binding.onboardingScreenRv.adapter = adaptor
       val llm = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
       binding.onboardingScreenRv.layoutManager = llm
       binding.onboardingScreenRv.setHasFixedSize(true)
       binding.onboardingScreenRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
           override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
               super.onScrollStateChanged(recyclerView, newState)
               // change the top view text value
               currentVisible = llm.findLastVisibleItemPosition()
               if (newState == 0) {
                   binding.onboardingScreenRv.smoothScrollToPosition(currentVisible)
               }
           }
       })
       adaptor.differ.submitList(getAllOnBoardingScreen())
       adaptor.setOnJoinClickListener {
           SharedPrefUtil.putAppFirstTimeOpen(requireContext())
           findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToSignUpStep1Fragment2())
       }

       adaptor.setOnLoginClickListener {
           SharedPrefUtil.putAppFirstTimeOpen(requireContext())
           findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToLoginFragment())

       }


    }


}