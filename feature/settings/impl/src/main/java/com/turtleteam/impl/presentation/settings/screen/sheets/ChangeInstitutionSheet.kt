package com.turtleteam.impl.presentation.settings.screen.sheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.api.models.Institution
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.layout.ErrorLayout
import com.turtleteam.core_view.view.sheet.SheetItem

@Composable
fun ChangeInstitutionSheet(
    loadingState: LoadingState,
    institutions: List<Institution>,
    selectedInstitution: Institution,
    onRefresh: () -> Unit,
    onSelectInstitutionClick: (Institution) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(TurtleTheme.color.sheetBackground)
            .padding(horizontal = 16.dp)
            .padding(bottom = 25.dp),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(TurtleTheme.color.sheetBackground)
        ) {
            when (loadingState) {
                LoadingState.Loading -> {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 25.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        CircularProgressIndicator(
                            Modifier.size(24.dp),
                            color = TurtleTheme.color.textColor,
                        )
                    }
                }

                LoadingState.Success -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(items = institutions) { institution ->
                            SheetItem(
                                modifier = Modifier.padding(bottom = 5.dp),
                                title = institution.title ?: "",
                                isSelected = selectedInstitution == institution,
                            ) {
                                onSelectInstitutionClick(institution)
                            }
                        }
//                    item {
//                        Spacer(Modifier)
//                    }
//                    item {
//                        Spacer(modifier = Modifier.padding(bottom = 28.dp))
//                    }
                    }
                }

                is LoadingState.Error -> ErrorLayout {
                    onRefresh()
                }

                else -> {}
            }
        }
    }
}