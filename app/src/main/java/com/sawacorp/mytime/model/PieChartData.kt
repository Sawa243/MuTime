package com.sawacorp.mytime.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

data class PieChartData(val slices: List<Slice>) {
    internal val totalLength: Float
        get() {
            return slices.map { it.value }.sum()
        }

    @Entity(tableName = "storage_entity")
    data class Slice(@PrimaryKey val value: Float, val color: Color, val name: String)
}