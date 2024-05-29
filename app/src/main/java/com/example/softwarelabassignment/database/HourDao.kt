package com.example.softwarelabassignment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.softwarelabassignment.model.hourModels.DaysDataItem

@Dao
interface HourDao {

    @Query("DELETE FROM day_table")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addDay(dayItem: DaysDataItem)

    @Delete
   suspend fun delete(dayItem: DaysDataItem)

    @Query("SELECT * FROM day_table")
    fun getAllDays():LiveData<List<DaysDataItem>?>

    @Query("SELECT * FROM day_table")
    suspend fun getAllBookmarkOnce():List<DaysDataItem>
    @Query("SELECT * FROM day_table WHERE id = :parentId")
   suspend fun getDayItemFromId(parentId: Int): DaysDataItem

}