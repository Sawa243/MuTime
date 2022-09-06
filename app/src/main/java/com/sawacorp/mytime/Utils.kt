package com.sawacorp.mytime

import java.text.SimpleDateFormat
import java.util.*

fun getDateToInt(): Int {
    val sdf = SimpleDateFormat("dd/M/yyyy", Locale.getDefault())
    val currentDate = sdf.format(Date())
    val parts = currentDate.split("/")
    val days = parts[0].toInt()
    val months = parts[1].toInt()
    val years = parts[2].toInt()
    return years * 365 + months * 30 + days
}