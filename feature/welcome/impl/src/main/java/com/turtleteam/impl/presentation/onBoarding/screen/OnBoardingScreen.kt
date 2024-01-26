package com.turtleteam.impl.presentation.presentation.onBoarding.screen

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.view.button.CommonButton
import com.turtleteam.core_view.view.pager.PageIndicator
import com.turtleteam.impl.presentation.onBoarding.screen.component.OnBoardingItem
import com.turtleteam.impl.presentation.onBoarding.viewModel.OnBoardingViewModel
import kotlinx.coroutines.launch

data class OnBoarding(
    val title: String,
    @DrawableRes
    val icon: Int,
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(viewModel: OnBoardingViewModel) {
    val scope = rememberCoroutineScope()

    val onBoardingList = listOf(
        OnBoarding(
            title = "Просмотр расписания в оффлайн-режиме",
            icon = R.drawable.ic_onboarding_schedule,
        ),
        OnBoarding(
            title = "Удобный и интуитивный интерфейс",
            icon = R.drawable.ic_onboarding_interface,
        ),
        OnBoarding(
            title = "Уведомления об изменениях в расписании",
            icon = R.drawable.ic_onboarding_notifications,
        ),
    )

    val pagerState = rememberPagerState { onBoardingList.size }

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 16.dp),
                pageSpacing = 16.dp,
            ) {
                OnBoardingItem(onBoarding = onBoardingList[it])
            }
            PageIndicator(
                currentPage = pagerState.currentPage,
                count = onBoardingList.size,
                modifier = Modifier.padding(top = 35.dp),
            )
        }
        CommonButton(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 46.dp, start = 32.dp, end = 32.dp),
            title = "ДАЛЕЕ",
            textColor = TurtleTheme.color.commonButtonTextColor,
            background = TurtleTheme.color.commonButtonBackground,
        ) {
            if (pagerState.currentPage == onBoardingList.lastIndex) {
                viewModel.navigateToWelcome()
            } else {
                scope.launch {
                    pagerState.animateScrollToPage(
                        pagerState.currentPage + 1,
                        animationSpec = spring(stiffness = Spring.StiffnessLow),
                    )
                }
            }
        }
    }
}
