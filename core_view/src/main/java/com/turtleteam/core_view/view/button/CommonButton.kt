package com.turtleteam.core_view.view.button

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.theme.fontQanelas
import com.turtleteam.core_view.utils.SelectButtonIndicator

@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    title: String,
    indicationColor: Color = Color.Transparent,
    textColor: Color,
    background: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(background)
            .clickable(
                onClick = onClick,
                interactionSource = MutableInteractionSource(),
                indication = SelectButtonIndicator(indicationColor, 12.dp)
            ),
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = fontQanelas,
                    color = textColor,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(vertical = 20.dp)
            )
        }
    }
}
