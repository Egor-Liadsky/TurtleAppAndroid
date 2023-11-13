package com.turtleteam.core_view.view.frame

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.model.Pair
import com.turtleteam.core_view.model.PairInfo
import com.turtleteam.core_view.theme.LocalColors
import com.turtleteam.core_view.theme.LocalShapes
import com.turtleteam.core_view.utils.toDate

@Composable
fun PairItem(pair: Pair, scrollInProgress: Boolean) {
    val currentMillis = System.currentTimeMillis()
    val startMillis = pair.isoDateStart.toDate().time
    val endMillis = pair.isoDateEnd.toDate().time

    Box {
        if (currentMillis in (startMillis + 1) until endMillis) {
            val default = endMillis - startMillis
            val progress = (currentMillis - startMillis).toFloat()
            val end = default - progress
            CurrentPair(progress, end, pair, scrollInProgress)
        } else {
            Pair(pair, scrollInProgress)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxScope.CurrentPair(progress: Float, end: Float, pair: Pair, scrollInProgress: Boolean) {
    val pagerState = rememberPagerState { pair.pairInfo.size }
    val progressColor = LocalColors.current.numberBackground
    val endColor = LocalColors.current.pairInfo
    var height by remember { mutableStateOf(0) }
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Column(
            modifier = Modifier
                .width(55.dp)
                .height(with(LocalDensity.current) { height.toDp() }),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                fontSize = 20.sp,
                text = pair.pairInfo.first().start,
                color = progressColor,
            )
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .background(progressColor)
                    .weight(progress),
            )
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .weight(end)
                    .background(endColor),
            )
            Text(
                fontSize = 20.sp,
                text = pair.pairInfo.first().end,
                color = endColor,
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .shadow(4.dp, LocalShapes.current.medium)
                .background(LocalColors.current.baseItemBackground, LocalShapes.current.medium)
                .padding(start = 12.dp)
                .onGloballyPositioned {
                    height = it.size.height
                },
        ) {
            if (pair.pairInfo.size > 1) {
                HorizontalPager(
                    userScrollEnabled = !scrollInProgress,
                    state = pagerState,
                    flingBehavior = PagerDefaults.flingBehavior(
                        state = pagerState,
                        lowVelocityAnimationSpec = tween(
                            easing = LinearEasing,
                            durationMillis = 200,
                        ),
                    ),
                ) {
                    PairInfo(pair.pairInfo[it])
                }
            } else {
                PairInfo(pair.pairInfo.first())
            }
        }
    }

    Text(
        modifier = Modifier
            .padding(bottom = 15.dp, end = 11.dp)
            .size(25.dp)
            .background(LocalColors.current.numberBackground, CircleShape)
            .align(Alignment.BottomEnd),
        fontSize = 20.sp,
        text = pair.pairInfo.first().number.toString(),
        color = Color.White,
        textAlign = TextAlign.Center,
    )

    if (pair.pairInfo.size > 1) {
        PageIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 6.dp),
            count = pair.pairInfo.size,
            current = pagerState.currentPage,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxScope.Pair(pair: Pair, scrollInProgress: Boolean) {
    val pagerState = rememberPagerState { pair.pairInfo.size }

    Row(
        Modifier
            .fillMaxSize()
            .shadow(4.dp, LocalShapes.current.medium)
            .background(LocalColors.current.baseItemBackground, LocalShapes.current.medium),
    ) {
        Column(modifier = Modifier.width(71.dp), horizontalAlignment = Alignment.End) {
            Text(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(25.dp)
                    .background(LocalColors.current.numberBackground, CircleShape)
                    .align(Alignment.CenterHorizontally),
                fontSize = 20.sp,
                text = pair.pairInfo.first().number.toString(),
                color = Color.White,
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier
                    .padding(top = 3.dp, end = 6.dp),
                fontSize = 20.sp,
                text = pair.pairInfo.first().start,
                color = LocalColors.current.numberBackground,
            )
            Text(
                modifier = Modifier
                    .padding(end = 8.dp),
                fontSize = 14.sp,
                text = pair.pairInfo.first().end,
                color = LocalColors.current.pairInfo,
            )
        }
        if (pair.pairInfo.size > 1) {
            HorizontalPager(
                userScrollEnabled = !scrollInProgress,
                state = pagerState,
                flingBehavior = PagerDefaults.flingBehavior(
                    state = pagerState,
                    lowVelocityAnimationSpec = tween(
                        easing = LinearEasing,
                        durationMillis = 200,
                    ),
                ),
            ) {
                PairInfo(pair.pairInfo[it])
            }
        } else {
            PairInfo(pair.pairInfo.first())
        }
    }
    if (pair.pairInfo.size > 1) {
        PageIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 6.dp),
            count = pair.pairInfo.size,
            current = pagerState.currentPage,
        )
    }
}

@Composable
fun PairInfo(pair: PairInfo) {
    Column(
        Modifier
            .padding(end = 11.dp)
            .padding(vertical = 14.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 40.dp)
                .background(LocalColors.current.doctrineBackground, RoundedCornerShape(35.dp))
                .padding(horizontal = 15.dp, vertical = 6.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            Text(
                text = pair.doctrine,
                fontSize = if (pair.doctrine.length > 20) 14.sp else 18.sp,
                color = LocalColors.current.textColor,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 11.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_teachers),
                contentDescription = "",
                tint = LocalColors.current.pairInfo,
            )
            Text(
                text = pair.teacher,
                fontSize = 14.sp,
                color = LocalColors.current.pairInfo,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_auditorium),
                contentDescription = "",
                tint = LocalColors.current.pairInfo,
            )
            Text(
                text = pair.auditoria,
                fontSize = 14.sp,
                color = LocalColors.current.pairInfo,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_corpus),
                contentDescription = "",
                tint = LocalColors.current.pairInfo,
            )
            Text(
                text = pair.corpus,
                fontSize = 14.sp,
                color = LocalColors.current.pairInfo,
            )
        }
    }
}

@Composable
fun PageIndicator(modifier: Modifier = Modifier, count: Int, current: Int) {
    val enabled = LocalColors.current.numberBackground
    val disabled = LocalColors.current.pairInfo

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
    ) {
        repeat(count) { iteration ->
            Canvas(modifier = Modifier.size(5.dp), onDraw = {
                drawCircle(if (current == iteration) enabled else disabled)
            })
        }
    }
}
