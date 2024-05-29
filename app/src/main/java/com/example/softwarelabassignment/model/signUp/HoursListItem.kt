package com.example.softwarelabassignment.model.signUp

import com.example.softwarelabassignment.model.hourModels.DayItemSelectedStatus

data class HoursListItem(
    val id:Int,
    val name:String,
    var selectStatus:HourSelectedStatus,
    val parentId:Int
)
enum class HourSelectedStatus{
    Selected,
    NotSelected
}
