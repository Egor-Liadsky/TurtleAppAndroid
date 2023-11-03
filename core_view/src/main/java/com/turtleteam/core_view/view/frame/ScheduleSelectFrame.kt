package com.turtleteam.core_view.view.frame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas
import com.turtleteam.core_view.view.button.SelectButton

@Composable
fun ScheduleSelectFrame(
    title: String,
    isGroup: Boolean = false,
    selectedItem: Boolean,
    selectButtonTitle: String,
    onClick: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .background(TurtleTheme.color.transparentBackground, TurtleTheme.shapes.medium)
            .padding(horizontal = 23.dp, vertical = 25.dp)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Column(Modifier.padding(top = 36.dp)) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_down),
                    contentDescription = null,
                    modifier = Modifier
                        .width(22.dp)
                        .height(62.dp),
                    tint = TurtleTheme.color.selectScheduleArrowColor
                )
            }

            Column {
                Text(
                    text = "Выберите", style = TextStyle(
                        fontSize = if (isGroup) 24.sp else 36.sp,
                        fontFamily = fontQanelas,
                        brush = TurtleTheme.color.selectScheduleTitleColor,
                    )
                )
                Text(
                    text = title, style = TextStyle(
                        fontSize = if(isGroup) 36.sp else 24.sp,
                        fontFamily = fontQanelas,
                        brush = TurtleTheme.color.selectScheduleTitleColor,
                    )
                )
            }
        }

        SelectButton(
            Modifier.padding(top = 10.dp),
            title = selectButtonTitle,
            indicationColor = TurtleTheme.color.secondText,
            selectedItem = selectedItem
        ) {
            onClick()
        }
    }
}
