package com.sawacorp.mytime.view.mainScreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp
import com.sawacorp.mytime.model.PieChartData
import com.sawacorp.mytime.render.ISliceDrawer
import com.sawacorp.mytime.render.SimpleSliceDrawer
import com.sawacorp.mytime.view.calculateAngle
import com.sawacorp.mytime.view.simpleChartAnimation

@Composable
fun MainScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        )
        Box(modifier = Modifier.size(260.dp), contentAlignment = Alignment.Center) {
            PieChart(
                pieChartData = PieChartData(
                    slices = listOf(
                        PieChartData.Slice(25F, Color.Red, ""),
                        PieChartData.Slice(45F, Color.Green, ""),
                        PieChartData.Slice(20F, Color.Blue, ""),
                    )
                )
            )
            Column() {
                Text(text = "Учеба")
                Text(text = "3:30")
                Card(modifier = Modifier.size(40.dp)) {
                    Text(text = "кнопка")
                }
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(text = "Мои Активности")
                Text(text = "Неделя")
            }
            Card(modifier = Modifier.size(40.dp)) {
                Text(text = "кнопка")
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)) {
                listOf(
                    PieChartData.Slice(25F, Color.Red, "Прогулка"),
                    PieChartData.Slice(45F, Color.Green, "Работа"),
                    PieChartData.Slice(20F, Color.Blue, "Забота"),
                ).forEach { item ->
                    item {
                        Row(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
                            Spacer(modifier = Modifier
                                .size(width = 5.dp, height = 40.dp)
                                .background(item.color))
                            Text(modifier = Modifier.padding(start = 10.dp), text = item.name)
                            Text(text = item.value.toString())
                            Card(modifier = Modifier.size(15.dp)) {
                                Text(text = "кнопка")
                            }
                            Card(modifier = Modifier.size(15.dp)) {
                                Text(text = "кнопка")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PieChart(
    pieChartData: PieChartData,
    modifier: Modifier = Modifier,
    animation: AnimationSpec<Float> = simpleChartAnimation(),
    sliceDrawer: ISliceDrawer = SimpleSliceDrawer()
) {
    val transitionProgress = remember(pieChartData.slices) { Animatable(initialValue = 0F) }

    LaunchedEffect(pieChartData.slices) {
        transitionProgress.animateTo(1F, animationSpec = animation)
    }

    DrawChart(
        pieChartData = pieChartData,
        modifier = modifier.fillMaxSize(),
        progress = transitionProgress.value,
        sliceDrawer = sliceDrawer
    )
}

@Composable
private fun DrawChart(
    pieChartData: PieChartData,
    modifier: Modifier,
    progress: Float,
    sliceDrawer: ISliceDrawer
) {
    val slices = pieChartData.slices

    Canvas(modifier = modifier) {
        drawIntoCanvas {
            var startArc = 0F
            slices.forEach { slice ->
                val arc = calculateAngle(
                    sliceLength = slice.value,
                    totalLength = pieChartData.totalLength,
                    progress = progress
                )
                sliceDrawer.drawSlice(
                    drawScope = this,
                    canvas = drawContext.canvas,
                    area = size,
                    startAngle = startArc,
                    sweepAngle = arc,
                    slice = slice
                )
                startArc += arc
            }
        }
    }
}