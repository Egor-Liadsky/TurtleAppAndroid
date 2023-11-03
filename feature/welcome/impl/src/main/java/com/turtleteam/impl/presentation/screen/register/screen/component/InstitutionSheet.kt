package com.turtleteam.impl.presentation.screen.register.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.api.data.model.Institution
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas
import com.turtleteam.impl.presentation.screen.register.viewModel.RegisterViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InstitutionSheet(sheetState: ModalBottomSheetState, institutions: List<Institution>, registerViewModel: RegisterViewModel) {
    val scope = rememberCoroutineScope()
    Column(
        Modifier
            .fillMaxSize()
            .background(TurtleTheme.color.sheetBackground)
            .padding(horizontal = 16.dp)
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
        Divider(
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 20.dp)
        )
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(items = institutions) { institution ->
                InstitutionItem(institution) {
                    registerViewModel.onSelectInstitutionClick(it)
                    scope.launch { sheetState.hide() }
                }
            }
        }
    }
}
