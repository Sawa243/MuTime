package com.sawacorp.mytime.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sawacorp.mytime.R
import com.sawacorp.mytime.ui.theme.*

@Composable
fun PopUpPeriod(
    setPeriod: (period: String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.3f)
            .background(colorResource(id = R.color.black))
    )
    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.width(280.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = White80)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Период",
                    style = mainStyle,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(
                        top = 24.dp,
                        bottom = 16.dp
                    )
                )
                Text(
                    text = "Задайте период активности",
                    style = mainStyle,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 24.dp),
                    color = Grey60
                )
                Text(
                    text = "Сегодня",
                    style = itemStylePeriod,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                        .clickable {
                            setPeriod("Сегодня")
                        }
                )
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .width(230.dp)
                        .background(Grey90)
                )
                Text(
                    text = "Завтра",
                    style = itemStylePeriod,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                        .clickable {
                            setPeriod("Завтра")
                        }
                )
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .width(230.dp)
                        .background(Grey90)
                )
                Text(
                    text = "Неделя",
                    style = itemStylePeriod,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                        .clickable {
                            setPeriod("Неделя")
                        }
                )
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .width(230.dp)
                        .background(Grey90)
                )
                Text(
                    text = "Задать период",
                    style = itemStylePeriod,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                        .clickable {
                            setPeriod("период")
                        }
                )
            }
        }
    }
}