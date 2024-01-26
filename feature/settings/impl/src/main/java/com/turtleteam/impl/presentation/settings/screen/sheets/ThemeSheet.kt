package com.turtleteam.impl.presentation.settings.screen.sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.sheet.SheetItem

@Composable
fun ThemeSheet(
    selectedIsDarkTheme: Boolean,
    onThemeSelected: (Boolean) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(TurtleTheme.color.sheetBackground)
            .padding(horizontal = 16.dp)
            .padding(bottom = 25.dp),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                SheetItem(title = "Светлая", isSelected = !selectedIsDarkTheme) {
                    onThemeSelected(false)
                }
            }
            item {
                SheetItem(title = "Темная", isSelected = selectedIsDarkTheme) {
                    onThemeSelected(true)
                }
            }
        }
    }
}