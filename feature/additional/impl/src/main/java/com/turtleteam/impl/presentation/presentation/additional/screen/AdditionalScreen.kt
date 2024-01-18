package com.turtleteam.impl.presentation.presentation.additional.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.core_view.view.button.NextButton
import com.turtleteam.core_view.view.progressBar.CommonProgressBar
import com.turtleteam.core_view.view.topbar.CommonTopBar
import com.turtleteam.impl.presentation.presentation.additional.screen.components.RingList
import com.turtleteam.impl.presentation.presentation.additional.viewModel.AdditionalViewModel

@Composable
fun AdditionalScreen(modifier: Modifier = Modifier, viewModel: AdditionalViewModel) {
    val state = viewModel.state.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .then(modifier),
    ) {
        CommonTopBar(title = "Дополнительно")

        LazyColumn(
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item {
                NextButton(
                    Modifier.padding(horizontal = 16.dp),
                    title = "Расписание звонков",
                    isArrowDown = state.value.ringsOpened,
                ) {
                    viewModel.onRingsClick()
                }

                if (state.value.ringsOpened) {
                    when (state.value.ringsLoadingState) {
                        LoadingState.Loading -> CommonProgressBar(Modifier.padding(top = 16.dp))

                        LoadingState.Success -> {
                            state.value.rings?.let {
                                RingList(rings = state.value.rings!!)
                            }
                        }

                        else -> {}
                    }
                }
            }

            item {
                NextButton(Modifier.padding(horizontal = 16.dp), title = "Замены") {
                }
            }

            item {
                NextButton(Modifier.padding(horizontal = 16.dp), title = "Планшетка") {
                }
            }
        }
    }
}
