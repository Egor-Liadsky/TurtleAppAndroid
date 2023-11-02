package com.turtleteam.impl.presentation.screen.group

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun GroupScreen(modifier: Modifier = Modifier) {
    Column(Modifier.fillMaxSize().then(modifier)) {
        Text("Group Screen")
    }
}
