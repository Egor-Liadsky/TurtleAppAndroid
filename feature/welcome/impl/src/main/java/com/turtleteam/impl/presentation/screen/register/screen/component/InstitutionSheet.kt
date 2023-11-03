package com.turtleteam.impl.presentation.screen.register.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas
import com.turtleteam.core_view.view.layout.ErrorLayout
import com.turtleteam.impl.presentation.screen.register.viewModel.RegisterViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InstitutionSheet(sheetState: ModalBottomSheetState, registerViewModel: RegisterViewModel) {

    val state = registerViewModel.state.collectAsState()
    val scope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxWidth()
            .background(TurtleTheme.color.sheetBackground)
            .padding(horizontal = 16.dp)
            .padding(bottom = 28.dp)
    ) {
        Text(
            text = "Ваше учебное заведение",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = fontQanelas,
                color = TurtleTheme.color.textColor,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth()
        )
        HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 20.dp)
                .clip(
                    RoundedCornerShape(3.dp)
                ), color = Color(0xFFB9B9B9)
        )

        when (state.value.institutionLoadingState) {

            LoadingState.Loading -> {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        Modifier.size(24.dp),
                        color = TurtleTheme.color.textColor
                    )
                }
            }

            LoadingState.Success -> {
                LazyVerticalGrid(
                    modifier = Modifier.padding(bottom = 28.dp),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(items = state.value.institutions ?: listOf()) { institution ->
                        InstitutionItem(institution, state.value.selectInstitution) {
                            registerViewModel.onSelectInstitutionClick(institution)
                            scope.launch { sheetState.hide() }
                        }
                    }
                }
            }

            is LoadingState.Error -> ErrorLayout()

            else -> {}
        }
    }
}
