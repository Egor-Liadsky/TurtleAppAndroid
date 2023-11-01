package com.turtleteam.core_view.viewModel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

abstract class BaseViewModel(private val navController: NavController): ViewModel() {

    fun onBackButtonClick() {
        navController.popBackStack()
    }

    suspend fun handleResult(
        execute: suspend () -> Unit,
        onFailure: (suspend (exception: Throwable) -> Unit)? = null,
        finally: (suspend () -> Unit)? = null
    ){
        try {
            execute()
        } catch (e: Throwable){
            onFailure?.invoke(e)
        } finally {
            finally?.invoke()
        }
    }
}
