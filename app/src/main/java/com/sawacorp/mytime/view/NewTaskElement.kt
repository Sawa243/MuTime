package com.sawacorp.mytime.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.sawacorp.mytime.R
import com.sawacorp.mytime.ui.theme.mainStyle

@Preview(showBackground = true)
@Composable
fun NewTaskElement() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.3f)
            .background(colorResource(id = R.color.black))
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Text(
                text = "Название Деятельности",
                style = mainStyle
            )
        }
    }
}