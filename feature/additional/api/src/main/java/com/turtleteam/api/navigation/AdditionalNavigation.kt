package com.turtleteam.api.navigation

import com.turtleteam.core_navigation.NavigationApi

interface AdditionalNavigation: NavigationApi {

    val baseRoute: String
}
