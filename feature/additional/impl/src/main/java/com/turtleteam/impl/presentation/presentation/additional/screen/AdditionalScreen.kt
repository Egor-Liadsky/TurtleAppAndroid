package com.turtleteam.impl.presentation.presentation.additional.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.core_view.view.button.NextButton
import com.turtleteam.core_view.view.topbar.CommonTopBar
import com.turtleteam.impl.presentation.presentation.additional.viewModel.AdditionalViewModel

@Composable
fun AdditionalScreen(modifier: Modifier = Modifier, viewModel: AdditionalViewModel) {

    val state = viewModel.state.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        CommonTopBar(title = "Дополнительно")

        LazyColumn(
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                NextButton(title = "Расписание звонков", isArrowDown = state.value.ringsOpened) {
                    viewModel.onRingsClick()
                }

                if (state.value.ringsOpened) {
                    when (state.value.ringsLoadingState) {

                        LoadingState.Loading -> CircularProgressIndicator()

                        LoadingState.Success -> {
                            state.value.rings?.value?.forEach { ring ->
                                Text(text = ring.toString())
                            }
                        }

                        LoadingState.Empty -> {}

                        is LoadingState.Error -> {}
                    }
                }
            }

            item {
                NextButton(title = "Замены") {

                }
            }

            item {
                NextButton(title = "Планшетка") {

                }
            }
        }
    }
}
