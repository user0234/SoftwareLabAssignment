package com.example.softwarelabassignment.model.signUp

data class DaysListItem(
    val id:Int,
    val dayName:String,
    val daySelectedCurrent:Boolean,
    val daySelectedHours:List<HoursListItem>,
)
