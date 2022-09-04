package com.sawacorp.mytime.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.sawacorp.mytime.R
import com.sawacorp.mytime.model.PieChartData
import com.sawacorp.mytime.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NewTaskElement(
    item: PieChartData.Slice? = null,
    showPopUp: (nameTask: String, colorTask: Color) -> Unit = { _, _ -> },
    exitPopUp: () -> Unit = {},
    deleteTask: () -> Unit = {}
) {

    var nameTask by remember {
        mutableStateOf("")
    }

    var showColorPicker by remember {
        mutableStateOf(false)
    }
    var color by remember {
        mutableStateOf(Color(R.color.black))
    }
    val controller = rememberColorPickerController()
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.3f)
            .background(colorResource(id = R.color.black))
            .clickable { exitPopUp() }
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier.width(280.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = White80)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp)
            ) {
                Text(
                    text = if (item == null) "Добавить активность" else "Изменить активность",
                    style = mainStyle
                )
                Spacer(
                    Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = nameTask,
                    onValueChange = { value -> if (value.length <= 25) nameTask = value },
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    placeholder = { Text(text = item?.name ?: "название") },
                    maxLines = 1,
                    shape = RoundedCornerShape(15.dp),
                    singleLine = true,
                    textStyle = itemStyleText,
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Black90)
                )
                Spacer(
                    Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "Цвет",
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                    style = mainStyle,
                    fontSize = 14.sp,
                    color = Grey60,
                    textAlign = TextAlign.Start
                )
                Spacer(
                    Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )
                LazyRow(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(
                        listOf(
                            Color10,
                            Color11,
                            Color12,
                            Color13,
                            Color14,
                        )
                    ) {
                        Button(
                            onClick = {
                                focusManager.clearFocus()
                                color = it
                            },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = it),
                            contentPadding = PaddingValues(),
                            modifier = Modifier
                                .padding(start = 5.dp, end = 5.dp)
                                .size(40.dp),
                        ) {}
                    }
                }
                Spacer(
                    Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )
                LazyRow(
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(
                        listOf(
                            Color15,
                            Color16,
                            Color17,
                            Color18,
                            Color19
                        )
                    ) {
                        Button(
                            onClick = {
                                color = it
                                focusManager.clearFocus()
                            },
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = it),
                            contentPadding = PaddingValues(),
                            modifier = Modifier
                                .padding(start = 5.dp, end = 5.dp)
                                .size(40.dp),
                        ) {}
                    }
                }
                Spacer(
                    Modifier
                        .height(20.dp)
                        .fillMaxWidth()
                )
                Button(
                    onClick = { showPopUp(nameTask, color) },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = Modifier.width(250.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .background(Brush.horizontalGradient(colors = listOf(Color10, Color19)))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .width(250.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (item == null) "Добавить задачу" else "Сохранить изменения",
                            style = itemStylePeriod,
                            color = Black,
                        )
                    }
                }
                Spacer(
                    Modifier
                        .height(5.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "Выбрать другой цвет",
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                        .clickable {
                            focusManager.clearFocus()
                            showColorPicker = !showColorPicker
                        },
                    style = mainStyle,
                    fontSize = 14.sp,
                    color = Purple40,
                    textAlign = TextAlign.Center
                )
                if (item != null) {
                    Spacer(
                        Modifier
                            .height(10.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Удалить активность",
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .fillMaxWidth()
                            .clickable {
                                focusManager.clearFocus()
                                deleteTask()
                            },
                        style = mainStyle,
                        fontSize = 14.sp,
                        color = Color20,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
    AnimatedVisibility(visible = showColorPicker) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            HsvColorPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(10.dp),
                controller = controller,
                onColorChanged = { colorEnvelope: ColorEnvelope ->
                    color = colorEnvelope.color
                }
            )
            Spacer(
                Modifier
                    .height(20.dp)
                    .fillMaxWidth()
            )
            Button(
                onClick = { showColorPicker = !showColorPicker },
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(),
                modifier = Modifier.width(250.dp),
            ) {
                Box(
                    modifier = Modifier
                        .background(Brush.horizontalGradient(colors = listOf(color, color)))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .width(250.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Выбрать",
                        style = itemStyleText
                    )
                }
            }
        }
    }

}