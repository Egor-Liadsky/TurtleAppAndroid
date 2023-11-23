package com.turtleteam.core_view.view.progressBar

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.theme.TurtleTheme

@Composable
fun CommonProgressBar(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier.size(30.dp), color = TurtleTheme.color.textColor)
}
