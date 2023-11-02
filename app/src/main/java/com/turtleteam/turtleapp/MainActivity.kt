package com.turtleteam.turtleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.navigation.compose.rememberNavController
import com.turtleteam.core_view.theme.TurtleAppTheme
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.background.TurtlesBackground
import com.turtleteam.turtleapp.navigation.MainNavigationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            TurtleAppTheme(darkTheme = false) {
                MainNavigationScreen(navController = navController)
            }
        }
    }
}
