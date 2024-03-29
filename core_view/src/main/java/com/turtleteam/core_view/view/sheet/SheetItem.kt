package com.turtleteam.core_view.view.sheet

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas
import com.turtleteam.core_view.utils.SelectButtonIndicator

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun SheetItem(
    modifier: Modifier = Modifier,
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier
            .height(40.dp)
            .fillMaxWidth()
            .background(
                TurtleTheme.color.blocks,
                shape = RoundedCornerShape(12.dp),
            )
            .border(
                1.dp,
                color = if (isSelected) {
                    TurtleTheme.color.primary
                } else {
                    TurtleTheme.color.stroke
                },
                shape = RoundedCornerShape(12.dp),
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                onClick = onClick,
                interactionSource = MutableInteractionSource(),
                indication = SelectButtonIndicator(TurtleTheme.color.primary, 12.dp),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = fontQanelas,
                color = TurtleTheme.color.textPrimary,
            ),
        )
    }
}
