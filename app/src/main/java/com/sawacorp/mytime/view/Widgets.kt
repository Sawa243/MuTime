package com.sawacorp.mytime.view

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.TweenSpec

fun simpleChartAnimation(): AnimationSpec<Float> = TweenSpec<Float>(durationMillis = 500)

internal fun calculateAngle(sliceLength: Float, totalLength: Float, progress: Float): Float =
    360F * sliceLength * progress / totalLength