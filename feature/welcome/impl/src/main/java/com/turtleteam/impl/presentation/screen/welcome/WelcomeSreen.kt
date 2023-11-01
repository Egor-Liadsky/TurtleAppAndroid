package com.turtleteam.impl.presentation.screen.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun WelcomeScreen() {
    Column(Modifier.fillMaxSize().background(Color.White)){
        Text("Welcome Screen")
    }
}
