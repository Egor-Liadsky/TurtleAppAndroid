package com.turtleteam.core_view.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.turtleteam.core_view.theme.TurtleTheme

data class NavigationItem(
    val route: String,
    @DrawableRes
    val icon: Int
)

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    routes: List<NavigationItem>,
    onClick: (route: String) -> Unit
) {
    var lastSelectedBtn by rememberSaveable { mutableStateOf(currentRoute) }

    BottomNavigation(
        modifier = Modifier
            .zIndex(0.9f)
            .fillMaxWidth()
            .background(TurtleTheme.color.bottomNavBarGradient),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        routes.forEach { item ->
            BottomNavigationItem(
                selected = lastSelectedBtn == item.route,
                onClick = { if (routes.map { it.route }.contains(currentRoute)) onClick(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = "",
                        tint = if (lastSelectedBtn == item.route) TurtleTheme.color.bottomNavMenuColors.getColor(true) else
                            TurtleTheme.color.bottomNavMenuColors.getColor(false)
                        ,
                        modifier = Modifier.size(24.dp)
                    )
                })
        }
    }

    LaunchedEffect(key1 = currentRoute, block = {
        if (routes.map { it.route }.contains(currentRoute)) lastSelectedBtn = currentRoute
    })
}
