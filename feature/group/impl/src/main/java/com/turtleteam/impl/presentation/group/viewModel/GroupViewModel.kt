package com.turtleteam.impl.presentation.group.viewModel

import androidx.lifecycle.ViewModel
import com.turtleteam.impl.navigation.GroupNavigator

class GroupViewModel(private val navigator: GroupNavigator): ViewModel() {

    fun navigateToWelcome() {
        navigator.navigateToWelcome()
    }
}
