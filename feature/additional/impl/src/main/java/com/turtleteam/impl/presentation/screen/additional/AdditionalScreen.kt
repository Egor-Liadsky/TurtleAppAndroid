package com.turtleteam.impl.presentation.screen.additional

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AdditionalScreen(modifier: Modifier = Modifier) {
    Column(Modifier.fillMaxSize().then(modifier).background(Color.White)) {
        Text("Additional Screen")
    }
}
