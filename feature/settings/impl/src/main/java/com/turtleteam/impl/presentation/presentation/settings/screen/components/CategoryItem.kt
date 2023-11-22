package com.turtleteam.impl.presentation.presentation.settings.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

@Composable
fun CategoryItem(title: String, list: List<MenuInfo>) {
    Column(
        Modifier
            .background(Color(0xFFF5F6F1).copy(0.76f), RoundedCornerShape(15.dp))
            .border(1.dp, Color(0xFF417B65).copy(0.35f), RoundedCornerShape(15.dp))
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
        ) {
            Text(
                text = title, style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = TurtleTheme.color.textColor
                ), modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 20.dp)
            )
            list.forEach {
                MenuItem(menuInfo = it)
            }
        }
    }
}