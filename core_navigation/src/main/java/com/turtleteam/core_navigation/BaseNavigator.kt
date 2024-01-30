package com.turtleteam.core_navigation

import android.annotation.SuppressLint
import androidx.navigation.NavController

abstract class BaseNavigator(private val navController: NavController) {

    @SuppressLint("RestrictedApi")
    fun onBackButtonClick() {
        navController.currentBackStack.value.let { //TODO посмотреть что не так
            if (it.size > 3) {
                navController.popBackStack()
            }
        }
    }
}
