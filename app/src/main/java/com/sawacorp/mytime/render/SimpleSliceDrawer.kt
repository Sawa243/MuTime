package com.sawacorp.mytime.render

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.sawacorp.mytime.model.PieChartData

class SimpleSliceDrawer(private val sliceThickness: Float = 15F) : ISliceDrawer {

    init {
        require(sliceThickness in 10F..100F) {
            "Thickness must be between 10 and 100, included"
        }
    }

    private val sectorPaint by lazy {
        Paint().apply {
            isAntiAlias = true
            style = PaintingStyle.Stroke
        }
    }

    private fun computeSectorThickness(area: Size): Float {
        val minSize = area.width.coerceAtMost(area.height)
        return sliceThickness * minSize / 200F
    }

    private fun computeDrawableArea(area: Size): Rect {
        val sliceThicknessOffset = computeSectorThickness(area) / 2F
        val horizontalOffset = (area.width - area.height) / 2F
        return Rect(
            left = sliceThicknessOffset + horizontalOffset,
            top = sliceThicknessOffset,
            right = area.width - sliceThicknessOffset - horizontalOffset,
            bottom = area.height - sliceThicknessOffset
        )
    }

    override fun drawSlice(
        drawScope: DrawScope,
        canvas: Canvas,
        area: Size,
        startAngle: Float,
        sweepAngle: Float,
        slice: PieChartData.Slice
    ) {
        val sliceThickness = computeSectorThickness(area)
        val drawableArea = computeDrawableArea(area)
        canvas.drawArc(
            rect = drawableArea,
            paint = sectorPaint.apply {
                color = Color(slice.color)
                strokeWidth = sliceThickness
                strokeCap = StrokeCap.Round
            },
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
        )
    }
}