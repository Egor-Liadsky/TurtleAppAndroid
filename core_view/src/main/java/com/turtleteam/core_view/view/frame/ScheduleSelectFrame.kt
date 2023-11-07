package com.turtleteam.core_view.view.frame

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.theme.TurtleTheme

@Composable
fun ScheduleSelectFrame(
    @DrawableRes
    image: Int,
    content: @Composable () -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .background(TurtleTheme.color.transparentBackground, TurtleTheme.shapes.medium)
            .padding(horizontal = 23.dp, vertical = 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(painter = painterResource(id = image), contentDescription = null)

        content()
    }
}
