package com.turtleteam.impl.presentation.presentation.additional.screen.layout

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.state.LoadingState
import com.turtleteam.core_view.view.button.AdditionalButton
import com.turtleteam.core_view.view.progressBar.CommonProgressBar
import com.turtleteam.core_view.view.topbar.CommonTopBar
import com.turtleteam.impl.presentation.presentation.additional.screen.components.RingList
import com.turtleteam.impl.presentation.presentation.additional.viewModel.AdditionalViewModel

@Composable
fun AdditionalLayout(modifier: Modifier, viewModel: AdditionalViewModel) {
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.getPlanshetkaUrl()
    }

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
                AdditionalButton(
                    Modifier.padding(horizontal = 16.dp),
                    title = "Расписание звонков",
                    isArrowDown = state.value.ringsOpened,
                ) {
                    viewModel.onRingsClick()
                }

                if (state.value.ringsOpened) {
                    when (state.value.ringsLoadingState) {

                        LoadingState.Loading -> CommonProgressBar(
                            Modifier.padding(
                                top = 16.dp,
                                bottom = 6.dp
                            )
                        )

                        LoadingState.Success -> {
                            state.value.rings?.let {
                                RingList(
                                    Modifier.padding(bottom = 6.dp),
                                    rings = state.value.rings!!
                                )
                            }
                        }

                        else -> {}
                    }
                }
            }

            item {
                AdditionalButton(Modifier.padding(horizontal = 16.dp), title = "Замены") {
                    viewModel.onChangesClick()
                }
            }

            item {
                AdditionalButton(Modifier.padding(horizontal = 16.dp), title = "Планшетка") {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(state.value.planshetkaUrl))
                    context.startActivity(intent)
                }
            }
        }
    }
}