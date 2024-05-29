package com.example.softwarelabassignment.utils

import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.model.StateItem
import com.example.softwarelabassignment.model.onBoarding.OnBoardingDataItem

object ConstantData {

    const val BASE_URL = "https://sowlab.com/assignment/"

    private val item1 = OnBoardingDataItem(
        0,
        "Quality",
        R.drawable.on_board_1,
        "Sell your farm fresh products directly to consumers, cutting out the middleman and reducing emissions of the global supply chain. ",
        1,
        "#5EA25F"
    )
    private val item2 = OnBoardingDataItem(
        1,
        "Convenient",
        R.drawable.on_board_2,
        "Our team of delivery drivers will make sure your orders are picked up on time and promptly delivered to your customers.",
        2,
        "#D5715B"
    )
    private val item3 = OnBoardingDataItem(
        2,
        "Local",
        R.drawable.on_board_3,
        "We love the earth and know you do too! Join us in reducing our local carbon footprint one order at a time. ",
        3,
        "#F8C569"
    )

    private val list = listOf<OnBoardingDataItem>(item3,item1,item2)

    fun getAllOnBoardingScreen(): List<OnBoardingDataItem> {
             return list
    }

    private val stateListValue = listOf(StateItem(1,"stateName1"),StateItem(2,"stateName2"),StateItem(3,"stateName3"))

    fun getStateList(): List<StateItem> {
           return stateListValue
    }
}