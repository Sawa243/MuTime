package com.sawacorp.mytime.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class PieChartData(val slices: List<Slice>) {
    internal val totalLength: Float
        get() {
            return slices.map { it.value }.sum()
        }

    @Entity(tableName = "storage_entity")
    data class Slice(
        var value: Float,
        var color: Int,
        @PrimaryKey
        var name: String
    )
}

/*
fun main() {
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault())
    val currentDate = sdf.format(Date())

    println(currentDate + Date())
}*/
