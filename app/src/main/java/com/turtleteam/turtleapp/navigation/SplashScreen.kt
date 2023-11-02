package com.turtleteam.turtleapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.turtleteam.storage.Storage
import org.koin.compose.koinInject

@Composable
fun SplashScreen(
    navController: NavController,
    welcomeRoute: String,
    groupRoute: String,
    storage: Storage = koinInject()
) {
    LaunchedEffect(key1 = Unit, block = {
        if (storage.getInstitution() == null) {
            navController.navigate(welcomeRoute)
        } else {
            navController.navigate(groupRoute)
        }
    })
    Column(Modifier.fillMaxSize().background(Color.Gray)) {

    }
}
