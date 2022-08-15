package com.sawacorp.mytime.view.mainScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sawacorp.mytime.R
import com.sawacorp.mytime.model.PieChartData
import com.sawacorp.mytime.ui.theme.Blue80
import com.sawacorp.mytime.ui.theme.itemStyleText
import com.sawacorp.mytime.ui.theme.itemStyleTime
import com.sawacorp.mytime.ui.theme.mainStyle
import com.sawacorp.mytime.view.PieChart
import com.sawacorp.mytime.view.PopUpPeriod

@Composable
fun MainScreen() {

    var showPopUp by remember {
        mutableStateOf(false)
    }

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
                        PieChartData.Slice(25F, Color.Red, "Color.Red"),
                        PieChartData.Slice(45F, Color.Green, "Color.Green"),
                        PieChartData.Slice(20F, Color.Blue, "Color.Blue"),
                        PieChartData.Slice(20F, Color.Yellow, "Color.Yellow"),
                    )
                )
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Учеба",
                    modifier = Modifier.padding(2.dp),
                    style = mainStyle
                )
                Text(
                    text = "3:30",
                    modifier = Modifier.padding(2.dp),
                    style = mainStyle,
                    fontSize = 34.sp,
                    fontWeight = FontWeight(500),
                    lineHeight = 40.sp,
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_pause),
                    contentDescription = "pause"
                )
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
                Text(
                    text = "Мои Активности",
                    style = mainStyle
                )
                Spacer(modifier = Modifier.height(18.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        showPopUp = !showPopUp
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = "add"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Неделя",
                        style = mainStyle,
                        color = Blue80,
                        fontFamily = FontFamily(Font(R.font.gotham_medium)),
                        lineHeight = 19.sp,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "add"
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp)
            ) {
                listOf(
                    PieChartData.Slice(25F, Color.Red, "Прогулка"),
                    PieChartData.Slice(45F, Color.Green, "Работа"),
                    PieChartData.Slice(20F, Color.Blue, "Забота"),
                    PieChartData.Slice(20F, Color.Yellow, "Дружба"),
                ).forEach { item ->
                    item {
                        Row(
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(0.5f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Spacer(
                                    modifier = Modifier
                                        .size(width = 5.dp, height = 40.dp)
                                        .background(item.color)
                                )
                                Text(
                                    modifier = Modifier.padding(start = 10.dp),
                                    text = item.name,
                                    style = itemStyleText
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = item.value.toString(),
                                    style = itemStyleTime
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(0.5f),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_change),
                                        contentDescription = "change"
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_play),
                                        contentDescription = "play"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    AnimatedVisibility(
        visible = showPopUp,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        PopUpPeriod() { showPopUp = !showPopUp }
    }
}