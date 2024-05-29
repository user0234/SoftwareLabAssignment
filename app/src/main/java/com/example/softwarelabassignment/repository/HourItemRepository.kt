package com.example.softwarelabassignment.repository

import com.example.softwarelabassignment.database.DayHourDataBase
import com.example.softwarelabassignment.model.hourModels.DaysDataItem

class HourItemRepository(private val database: DayHourDataBase) {

     suspend fun addNewDay(dayItem:DaysDataItem) {
            database.hourDao().addDay(dayItem)
      }
    suspend fun deleteDay(dayItem:DaysDataItem) {
        database.hourDao().delete(dayItem)
    }
    fun getAllItems() = database.hourDao().getAllDays()

    suspend fun getAllItemsOnce() = database.hourDao().getAllBookmarkOnce()
    suspend  fun getDayItemFromId(parentId: Int): DaysDataItem {
        return database.hourDao().getDayItemFromId(parentId)
    }

   suspend fun deleteAll() {
        database.hourDao().deleteAll()
    }

}