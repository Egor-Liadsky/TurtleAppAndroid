package com.turtleteam.impl.presentation.group.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.turtleteam.impl.presentation.group.viewModel.GroupViewModel

@Composable
fun GroupScreen(modifier: Modifier = Modifier, viewModel: GroupViewModel) {
    Column(
        Modifier
            .fillMaxSize()
            .then(modifier)) {
        Text("Group Screen")
        Button(onClick = { viewModel.navigateToWelcome() }) {
            Text(text = "Navigate to Welcome screen")
        }
    }
}
