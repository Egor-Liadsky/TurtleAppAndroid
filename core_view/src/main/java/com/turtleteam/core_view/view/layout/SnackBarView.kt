package com.turtleteam.core_view.view.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

@Composable
fun SnackBarView(snackbarHostState: SnackbarHostState) {
    SnackbarHost(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .padding(horizontal = 16.dp),
        hostState = snackbarHostState
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(
                    TurtleTheme.color.snackBarBackground,
                    shape = RoundedCornerShape(6.dp)
                )
                .clickable(
                    indication = null,
                    interactionSource = MutableInteractionSource()
                ) {
                    it.performAction()
                }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = it.message,
                style = TextStyle(
                    fontFamily = fontQanelas,
                    fontSize = 14.sp,
                    color = TurtleTheme.color.snackBarText
                )
            )

            TextButton(onClick = { it.performAction() }) {
                Text(
                    text = "Закрыть",
                    style = TextStyle(
                        fontFamily = fontQanelas,
                        fontSize = 14.sp,
                        color = TurtleTheme.color.snackBarText
                    )
                )
            }
        }
    }
}