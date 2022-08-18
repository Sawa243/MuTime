package com.sawacorp.mytime.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.sawacorp.mytime.R
import com.sawacorp.mytime.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NewTaskElement(
    showPopUp: (nameTask: String, colorTask: Color) -> Unit = { _, _ -> }
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
                    text = "Название Деятельности",
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
                    placeholder = { Text(text = "название") },
                    maxLines = 1,
                    shape = RoundedCornerShape(25.dp),
                    singleLine = true,
                    textStyle = itemStyleText,
                    colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Black90)
                )
                Spacer(
                    Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )
                Button(
                    onClick = {
                        focusManager.clearFocus()
                        showColorPicker = !showColorPicker
                    },
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = Modifier.width(250.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .background(Brush.horizontalGradient(colors = listOf(Pink80, Cyan)))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .width(250.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Выбрать цвет задачи",
                            style = itemStylePeriod
                        )
                    }
                }
                Spacer(
                    Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )
                Button(
                    onClick = { showPopUp(nameTask, color) },
                    shape = RoundedCornerShape(25.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = Modifier.width(250.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .background(Brush.horizontalGradient(colors = listOf(Color10, Color10)))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .width(250.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Добавить задачу",
                            style = itemStylePeriod
                        )
                    }
                }
                Spacer(
                    Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                )
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