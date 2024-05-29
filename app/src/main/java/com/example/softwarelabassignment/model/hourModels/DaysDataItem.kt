package com.example.softwarelabassignment.model.hourModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.softwarelabassignment.model.signUp.HoursListItem

@Entity(tableName = "day_table")
data class DaysDataItem(
    @PrimaryKey
    val id:Int,
    val value:String,
    var selectedStatus:DayItemSelectedStatus,
    val hourList:List<HoursListItem>
)

enum class DayItemSelectedStatus {
    Current,
    Not,
    Yse
}
