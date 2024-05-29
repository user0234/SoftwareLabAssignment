package com.example.softwarelabassignment.model.hourModels

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.softwarelabassignment.model.signUp.HoursListItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

@ProvidedTypeConverter
class Converter  {

    private val gson = Gson()
     @TypeConverter
     fun fromList(value:List<HoursListItem> ) : String {
         return gson.toJson(value)
     }

    @TypeConverter
    fun toList(value: String): List<HoursListItem>  {
        return gson.fromJson(value, object : TypeToken<List<HoursListItem>>() {
        }.type)
    }

}