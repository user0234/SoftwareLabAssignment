package com.example.softwarelabassignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.softwarelabassignment.model.hourModels.Converter
import com.example.softwarelabassignment.model.hourModels.DaysDataItem

@Database(entities = [DaysDataItem::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class DayHourDataBase : RoomDatabase() {

    abstract fun hourDao(): HourDao

    // code for single instance
    companion object {
        @Volatile
        private var instance: DayHourDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(this) {
            val INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                DayHourDataBase::class.java,
                "hour_database.db"
            )
                .addTypeConverter(Converter())
                .build()
            instance = INSTANCE

            instance
        }
    }
}