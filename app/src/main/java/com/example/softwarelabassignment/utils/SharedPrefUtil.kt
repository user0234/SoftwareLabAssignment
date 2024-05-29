package com.example.softwarelabassignment.utils

import android.content.Context

object SharedPrefUtil {

    // first time opening app
   private const val ON_BOARDING_SHARED_PREF = "onBoardingSharedPref"
    fun getAppFirstTimeOpen(context: Context):Boolean {
        return getSharedPrefBoolean(
            context,
            ON_BOARDING_SHARED_PREF,
            "firstTimeLoginIn",
            false
        )
    }

    fun putAppFirstTimeOpen(context: Context){
        putSharedPrefBoolean(context,ON_BOARDING_SHARED_PREF,"firstTimeLoginIn",true)
    }

    private const val DEVICE_TOKEN_SHARED_PREF = "tokenSharedPref"
    // logged in token value saved
    fun getDeviceLoggedInToken(context: Context):String {

        return getSharedPrefString(
            context,
            DEVICE_TOKEN_SHARED_PREF,
            "loginToken",
            "null"
        )
    }
    fun putDeviceLoggedInToken(context: Context,token:String){
        putSharedPrefString(context,DEVICE_TOKEN_SHARED_PREF,"loginToken",token)
    }



   private fun getSharedPrefBoolean(
        context: Context,
        tag: String,
        key: String,
        defaultValue: Boolean,
    ): Boolean {
        val sharedPref = context.getSharedPreferences(tag, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(key, defaultValue)
    }


    private fun putSharedPrefBoolean(context: Context, tag: String, key: String, value: Boolean) {
        val sharedPref = context.getSharedPreferences(tag, Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean(key, value).apply()
    }

    private fun getSharedPrefInt(
        context: Context,
        tag: String,
        key: String,
        defaultValue: Int,
    ): Int {
        val sharedPref = context.getSharedPreferences(tag, Context.MODE_PRIVATE)
        return sharedPref.getInt(key, defaultValue)
    }

    private fun putSharedPrefInt(context: Context, tag: String, key: String, value: Int) {
        val sharedPref = context.getSharedPreferences(tag, Context.MODE_PRIVATE)
        sharedPref.edit().putInt(key, value).apply()
    }


    private fun getSharedPrefFloat(
        context: Context,
        tag: String,
        value: String,
        defaultValue: Float,
    ): Float {
        val sharedPref = context.getSharedPreferences(tag, Context.MODE_PRIVATE)
        return sharedPref.getFloat(value, defaultValue)
    }

    private fun putSharedPrefFloat(context: Context, tag: String, key: String, value: Float) {
        val sharedPref = context.getSharedPreferences(tag, Context.MODE_PRIVATE)
        sharedPref.edit().putFloat(key, value).apply()
    }

    private fun getSharedPrefString(
        context: Context,
        tag: String,
        value: String,
        defaultValue: String,
    ): String {
        val sharedPref = context.getSharedPreferences(tag, Context.MODE_PRIVATE)
        return sharedPref.getString(value, defaultValue) ?: defaultValue
    }

    private  fun putSharedPrefString(context: Context, tag: String, key: String, value: String) {
        val sharedPref = context.getSharedPreferences(tag, Context.MODE_PRIVATE)
        sharedPref.edit().putString(key, value).apply()
    }

}