package com.turtleteam.impl.presentation.presentation.additional.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.model.RingInfo
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

@Composable
fun RingItem(name: String, rings: List<RingInfo>) {
    Column(
        modifier = Modifier
            .background(Color(0xFFF5F6F1).copy(0.76f), RoundedCornerShape(15.dp))
            .border(1.dp, Color(0xFF417B65).copy(0.35f), RoundedCornerShape(15.dp))
            .size(238.dp, 306.dp)
            .padding(vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 7.dp)
                .align(Alignment.CenterHorizontally),
            text = name,
            fontFamily = fontQanelas,
            fontSize = 18.sp,
            color = TurtleTheme.color.callTypeColor,
            fontWeight = FontWeight(700),
        )
        rings.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .background(TurtleTheme.color.numberBackground, CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = it.number.toString(),
                        fontFamily = fontQanelas,
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight(700),
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 23.dp)
                        .width(127.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    it.from?.let {
                        Text(
                            text = it,
                            fontFamily = fontQanelas,
                            fontSize = 20.sp,
                            fontWeight = FontWeight(700),
                            color = TurtleTheme.color.callTimeColor,
                        )
                    }

                    Text(
                        text = "-",
                        fontFamily = fontQanelas,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = TurtleTheme.color.callTimeColor,
                    )
                    it.to?.let {
                        Text(
                            text = it,
                            fontFamily = fontQanelas,
                            fontSize = 20.sp,
                            fontWeight = FontWeight(700),
                            color = TurtleTheme.color.callTimeColor,
                        )
                    }
                }
            }
        }
    }
}
