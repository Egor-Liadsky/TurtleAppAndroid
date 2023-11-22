package com.turtleteam.turtleapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.turtleteam.core_view.theme.TurtleAppTheme
import com.turtleteam.storage.Storage
import com.turtleteam.turtleapp.navigation.MainNavigationScreen
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val content: View = findViewById(android.R.id.content)
            val scope = rememberCoroutineScope()
            val storage: Storage = koinInject()
            val isWelcome = remember { mutableStateOf(false) }
            val isDarkTheme = storage.theme.collectAsState(initial = false)

            // Проверка на регистрацию пользователя, проводится при запуске splash-экрана
            LaunchedEffect(key1 = null) {
                content.viewTreeObserver.addOnPreDrawListener(
                    object : ViewTreeObserver.OnPreDrawListener {
                        override fun onPreDraw(): Boolean {
                            scope.launch { isWelcome.value = storage.getGroup() == null }
                            return if (isWelcome.value) {
                                content.viewTreeObserver.removeOnPreDrawListener(this)
                                isWelcome.value = true
                                true
                            } else {
                                content.viewTreeObserver.removeOnPreDrawListener(this)
                                isWelcome.value = false
                                false
                            }
                        }
                    },
                )
            }

            val navController = rememberNavController()
            TurtleAppTheme(darkTheme = isDarkTheme.value) {
                MainNavigationScreen(navController = navController, isWelcome.value)
            }
        }
    }
}
