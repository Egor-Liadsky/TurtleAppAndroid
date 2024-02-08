package com.turtleteam.impl.presentation.settings.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            .background(TurtleTheme.color.blocks, RoundedCornerShape(15.dp))
            .border(1.dp, TurtleTheme.color.stroke, RoundedCornerShape(15.dp)),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = TurtleTheme.color.textPrimary,
                ),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 20.dp),
            )
            list.forEach {
                MenuItem(menuInfo = it)
            }
        }
    }
}
