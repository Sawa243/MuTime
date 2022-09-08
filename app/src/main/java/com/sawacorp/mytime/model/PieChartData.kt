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
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var name: String,
        var value: Float,
        var color: Int,
        var date: Int
    )
}