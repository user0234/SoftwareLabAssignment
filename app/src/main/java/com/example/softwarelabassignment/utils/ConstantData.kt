package com.example.softwarelabassignment.utils

import com.example.softwarelabassignment.R
import com.example.softwarelabassignment.model.StateItem
import com.example.softwarelabassignment.model.hourModels.DayItemSelectedStatus
import com.example.softwarelabassignment.model.hourModels.DaysDataItem
import com.example.softwarelabassignment.model.onBoarding.OnBoardingDataItem
import com.example.softwarelabassignment.model.signUp.HourSelectedStatus
import com.example.softwarelabassignment.model.signUp.HoursListItem

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

    private val list = listOf<OnBoardingDataItem>(item3, item1, item2)

    fun getAllOnBoardingScreen(): List<OnBoardingDataItem> {
        return list
    }

    private val stateListValue =
        listOf(StateItem(1, "stateName1"), StateItem(2, "stateName2"), StateItem(3, "stateName3"))

    fun getStateList(): List<StateItem> {
        return stateListValue
    }

    private val hourMonItemsList: List<HoursListItem> =
        listOf(
            HoursListItem(1, "8:00am - 10:00am", HourSelectedStatus.NotSelected, 1),
            HoursListItem(2, "10:00am - 1:00pm", HourSelectedStatus.NotSelected, 1),
            HoursListItem(3, "1:00pm - 4:00pm", HourSelectedStatus.NotSelected, 1),
            HoursListItem(4, "4:00pm - 7:00pm", HourSelectedStatus.NotSelected, 1),
            HoursListItem(5, "7:00pm - 10:00pm", HourSelectedStatus.NotSelected, 1)
        )

    private val mondayItem = DaysDataItem(1, "M", DayItemSelectedStatus.Current, hourMonItemsList)

    private val hourTueItemsList: List<HoursListItem> =
        listOf(
            HoursListItem(1, "8:00am - 10:00am", HourSelectedStatus.NotSelected, 2),
            HoursListItem(2, "10:00am - 1:00pm", HourSelectedStatus.NotSelected, 2),
            HoursListItem(3, "1:00pm - 4:00pm", HourSelectedStatus.NotSelected, 2),
            HoursListItem(4, "4:00pm - 7:00pm", HourSelectedStatus.NotSelected, 2),
            HoursListItem(5, "7:00pm - 10:00pm", HourSelectedStatus.NotSelected, 2)
        )

    private val tuesdayItem = DaysDataItem(2, "T", DayItemSelectedStatus.Not, hourTueItemsList)


    private val hourWedItemsList: List<HoursListItem> =
        listOf(
            HoursListItem(1, "8:00am - 10:00am", HourSelectedStatus.NotSelected, 3),
            HoursListItem(2, "10:00am - 1:00pm", HourSelectedStatus.NotSelected, 3),
            HoursListItem(3, "1:00pm - 4:00pm", HourSelectedStatus.NotSelected, 3),
            HoursListItem(4, "4:00pm - 7:00pm", HourSelectedStatus.NotSelected, 3),
            HoursListItem(5, "7:00pm - 10:00pm", HourSelectedStatus.NotSelected, 3)
        )

    private val wednesdayItem = DaysDataItem(3, "W", DayItemSelectedStatus.Not, hourWedItemsList)

    private val hourThurItemsList: List<HoursListItem> =
        listOf(
            HoursListItem(1, "8:00am - 10:00am", HourSelectedStatus.NotSelected, 4),
            HoursListItem(2, "10:00am - 1:00pm", HourSelectedStatus.NotSelected, 4),
            HoursListItem(3, "1:00pm - 4:00pm", HourSelectedStatus.NotSelected, 4),
            HoursListItem(4, "4:00pm - 7:00pm", HourSelectedStatus.NotSelected, 4),
            HoursListItem(5, "7:00pm - 10:00pm", HourSelectedStatus.NotSelected, 4)
        )

    private val thursdayItem = DaysDataItem(4, "Th", DayItemSelectedStatus.Not, hourThurItemsList)

    private val hourFriItemsList: List<HoursListItem> =
        listOf(
            HoursListItem(1, "8:00am - 10:00am", HourSelectedStatus.NotSelected, 5),
            HoursListItem(2, "10:00am - 1:00pm", HourSelectedStatus.NotSelected, 5),
            HoursListItem(3, "1:00pm - 4:00pm", HourSelectedStatus.NotSelected, 5),
            HoursListItem(4, "4:00pm - 7:00pm", HourSelectedStatus.NotSelected, 5),
            HoursListItem(5, "7:00pm - 10:00pm", HourSelectedStatus.NotSelected, 5)
        )

    private val fridayItem = DaysDataItem(5, "F", DayItemSelectedStatus.Not, hourFriItemsList)

    private val hourSatItemsList: List<HoursListItem> =
        listOf(
            HoursListItem(1, "8:00am - 10:00am", HourSelectedStatus.NotSelected, 6),
            HoursListItem(2, "10:00am - 1:00pm", HourSelectedStatus.NotSelected, 6),
            HoursListItem(3, "1:00pm - 4:00pm", HourSelectedStatus.NotSelected, 6),
            HoursListItem(4, "4:00pm - 7:00pm", HourSelectedStatus.NotSelected, 6),
            HoursListItem(5, "7:00pm - 10:00pm", HourSelectedStatus.NotSelected, 6)
        )

    private val saturdayItem = DaysDataItem(6, "S", DayItemSelectedStatus.Not, hourSatItemsList)

    private val hourSunItemsList: List<HoursListItem> =
        listOf(
            HoursListItem(1, "8:00am - 10:00am", HourSelectedStatus.NotSelected, 7),
            HoursListItem(2, "10:00am - 1:00pm", HourSelectedStatus.NotSelected, 7),
            HoursListItem(3, "1:00pm - 4:00pm", HourSelectedStatus.NotSelected, 7),
            HoursListItem(4, "4:00pm - 7:00pm", HourSelectedStatus.NotSelected, 7),
            HoursListItem(5, "7:00pm - 10:00pm", HourSelectedStatus.NotSelected, 7)
        )

    private val sundayItem = DaysDataItem(7, "Su", DayItemSelectedStatus.Not, hourSunItemsList)


    fun getDaysItems(): List<DaysDataItem> {
        return listOf(mondayItem, tuesdayItem, thursdayItem, fridayItem, wednesdayItem, sundayItem,
            saturdayItem)
    }
}