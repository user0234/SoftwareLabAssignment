package com.example.softwarelabassignment.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.adaptors.DaysListAdaptor
import com.example.softwarelabassignment.adaptors.HoursListAdaptor
import com.example.softwarelabassignment.databinding.FragmentSignUpHoursBinding
import com.example.softwarelabassignment.model.BusinessHours
import com.example.softwarelabassignment.model.hourModels.DayItemSelectedStatus
import com.example.softwarelabassignment.model.signUp.HourSelectedStatus
import com.example.softwarelabassignment.ui.activity.HomeScreenActivity
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingActivity
import com.example.softwarelabassignment.ui.activity.onboarding.OnBoardingViewModel
import com.example.softwarelabassignment.utils.ConstantData
import com.example.softwarelabassignment.utils.Resource
import com.example.softwarelabassignment.utils.SharedPrefUtil


class SignUpHoursFragment : Fragment(R.layout.fragment_sign_up_hours) {


    private lateinit var binding: FragmentSignUpHoursBinding
    private lateinit var daysAdaptor: DaysListAdaptor
    private lateinit var hoursAdaptor: HoursListAdaptor
    private lateinit var vieModel: OnBoardingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpHoursBinding.bind(view)
        vieModel = (activity as OnBoardingActivity).viewModel

        setUpItemInDataBase()
        setUpDayRecyclerView()
        setUpHourRecyclerView()
        setUpDataObservable()
        setUpUi()
    }

    private fun setUpItemInDataBase() {
        val items = ConstantData.getDaysItems()

        items.forEach {
            vieModel.addNewDayItem(it)
        }
    }

    private fun setUpUi() {

        binding.btGoBack.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.btContinue.setOnClickListener {
            vieModel.getAllDayItems() {

                val daysHourList = it.map { dayItem ->
                    dayItem.hourList.filter { hourItem ->
                        hourItem.selectStatus == HourSelectedStatus.Selected
                    }.map { hourValue ->
                        hourValue.name
                    }
                }
                val businessHour = BusinessHours(
                    mon = daysHourList.get(0),
                    tue = daysHourList.get(1),
                    wed = daysHourList.get(2),
                    thu = daysHourList.get(3),
                    fri = daysHourList.get(4),
                    sat = daysHourList.get(5),
                    sun = daysHourList.get(6)
                )
                vieModel.signUpStep4(businessHour)
            }

        }

        vieModel.signUpResponse.observe(viewLifecycleOwner) {
            Log.i("SignUpResponse","response - ${it.data} , mesage - ${it.message}")

            when (it) {
                is Resource.Success -> {
                    Log.i("SignUpResponse","response - ${it.data}")
                    if (it.data != null &&it.data.success) {
                        if (it.data.token != "") {
                            SharedPrefUtil.putDeviceLoggedInToken(
                                this.requireContext(),
                                it.data.token
                            )

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
                    }else{
                        Toast.makeText(this.context, "${it.message}", Toast.LENGTH_SHORT).show()
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


    private fun setUpDataObservable() {
        vieModel.getAllDaysObservable().observe(viewLifecycleOwner) { dayItemList ->

            daysAdaptor.differ.submitList(dayItemList)

        }
    }

    private fun setUpHourRecyclerView() {
        hoursAdaptor = HoursListAdaptor()
        binding.timePickerRv.apply {
            adapter = hoursAdaptor
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        }

        hoursAdaptor.setOnItemClickListener {
            it.selectStatus = HourSelectedStatus.Selected
            vieModel.updateHourItem(it)
        }
    }

    private fun setUpDayRecyclerView() {
        daysAdaptor = DaysListAdaptor()
        binding.datePickerRv.apply {
            adapter = daysAdaptor
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }

        daysAdaptor.setOnItemSelectedListener {
            hoursAdaptor.notifyDataSetChanged()
            hoursAdaptor.differ.submitList(it.hourList)
        }

        daysAdaptor.setOnItemClickListener {
            it.selectedStatus = DayItemSelectedStatus.Current
            vieModel.updateDayItem(it)
        }

    }


}