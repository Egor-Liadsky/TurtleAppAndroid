package com.turtleteam.core_view.view.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

@Composable
fun SelectButton(
    modifier: Modifier = Modifier,
    title: String,
    isSelected: Boolean,
    trailingContent: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = TurtleTheme.color.selectButtonBackground),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        border = BorderStroke(
            1.dp,
            if (isSelected) TurtleTheme.color.selectButtonStrokeEnabled else TurtleTheme.color.selectButtonStrokeDisabled,
        ),
        contentPadding = PaddingValues(0.dp),
    ) {
        Box(Modifier.fillMaxSize()) {
            if (trailingContent != null) {
                Column(Modifier.align(Alignment.CenterEnd)) {
                    trailingContent()
                }
            }
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontFamily = fontQanelas,
                        color = if (isSelected) TurtleTheme.color.textPrimary else TurtleTheme.color.textAccent,
                        textAlign = TextAlign.Center,
                    ),
                )
            }
        }
    }
}
