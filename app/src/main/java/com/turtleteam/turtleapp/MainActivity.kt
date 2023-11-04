package com.turtleteam.turtleapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.turtleteam.core_view.theme.TurtleAppTheme
import com.turtleteam.storage.Storage
import com.turtleteam.turtleapp.navigation.MainNavigationScreen
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val content: View = findViewById(android.R.id.content)
            val scope = rememberCoroutineScope()
            val storage: Storage = koinInject()
            val isWelcome = mutableStateOf(false)

            content.viewTreeObserver.addOnPreDrawListener(
                object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        scope.launch { isWelcome.value = storage.getInstitution() == null }
                        return if (isWelcome.value) {
                            content.viewTreeObserver.removeOnPreDrawListener(this)
                            true
                        } else {
                            false
                        }
                    }
                },
            )

            val navController = rememberNavController()
            TurtleAppTheme(darkTheme = false) {
                MainNavigationScreen(navController = navController, isWelcome.value)
            }
        }
    }
}
