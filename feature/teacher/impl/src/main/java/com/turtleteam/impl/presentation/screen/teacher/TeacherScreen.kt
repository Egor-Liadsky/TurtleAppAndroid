package com.turtleteam.impl.presentation.screen.teacher

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun TeacherScreen(modifier: Modifier = Modifier) {
    Column(Modifier.fillMaxSize().then(modifier).background(Color.White)) {
        Text("Teacher Screen")
    }
}
