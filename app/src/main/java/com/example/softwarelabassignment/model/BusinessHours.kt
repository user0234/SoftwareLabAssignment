package com.example.softwarelabassignment.model

data class BusinessHours(
   // example: List [ "8:00am - 10:00am", "10:00am - 1:00pm" ]
    val fri: List<String>,
    val mon: List<String>,
    val sat: List<String>,
    val sun: List<String>,
    val thu: List<String>,
    val tue: List<String>,
    val wed: List<String>
)