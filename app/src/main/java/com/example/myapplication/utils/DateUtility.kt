package com.example.myapplication.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val Pattern_DD_MM_YYYY = "dd-MM-yyyy"
const val API_Pattern_DD_MM_YYYY = "yyyy-MM-dd'T'HH:mm:ss'Z'"
fun getFormattedDate(pattern:String, value:String,apiPattern:String = API_Pattern_DD_MM_YYYY): String? {

    val localDateTime = LocalDateTime.parse(value, DateTimeFormatter.ofPattern(apiPattern))
    return localDateTime.format(DateTimeFormatter.ofPattern(pattern))

}