package com.turtleteam.core_view.view.layout

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.api.models.Schedule
import com.turtleteam.core_view.theme.LocalColors
import com.turtleteam.core_view.theme.LocalShapes
import com.turtleteam.core_view.utils.toCalendar
import com.turtleteam.core_view.utils.toDayOfWeek
import com.turtleteam.core_view.utils.toMonth
import com.turtleteam.core_view.view.frame.DateItem
import com.turtleteam.core_view.view.frame.PairItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScheduleLayout(data: Schedule) {
    val visible = remember { MutableTransitionState(false) }
    val pagerState = rememberPagerState { data.days.size }
    val scope = rememberCoroutineScope()

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
    ) { page ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                top = 16.dp,
                end = 16.dp,
                start = 16.dp,
                bottom = 16.dp,
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            stickyHeader {
                val width = remember { mutableStateOf(0.dp) }
                val density = LocalDensity.current
                Column(
                    modifier = Modifier.onGloballyPositioned {
                        width.value = with(density) { it.size.width.toDp() }
                    },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    val popUpExpanded = remember { mutableStateOf(false) }
                    DateItem(day = data.days[page]) {
                        popUpExpanded.value = true
                    }
                    DatesPopup(
                        expanded = popUpExpanded.value,
                        onDismissRequest = { popUpExpanded.value = false },
                        list = data,
                        onItemClick = {
                            scope.launch {
                                popUpExpanded.value = false
                                delay(100)
                                pagerState.scrollToPage(it)
                            }
                        },
                        page,
                        width.value,
                    )
                }
            }
            items(items = data.days[page].pairs) {
                PairItem(pair = it, pagerState.isScrollInProgress)
            }
        }
    }
    DisposableEffect(key1 = visible, effect = {
        scope.launch {
            delay(150)
            visible.targetState = true
        }
        onDispose {}
    })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DatesPopup(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    list: Schedule,
    onItemClick: (Int) -> Unit,
    deleteIndex: Int,
    width: Dp,
) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(surface = LocalColors.current.baseItemBackground),
        shapes = MaterialTheme.shapes.copy(medium = LocalShapes.current.medium),
    ) {
        DropdownMenu(
            modifier = Modifier
                .width(width)
                .border(1.dp, Color(0xFF417B65).copy(0.35f), RoundedCornerShape(12.dp)),
            expanded = expanded,
            onDismissRequest = onDismissRequest
        ) {
            CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                list.days.forEachIndexed { index, day ->
                    if (index != deleteIndex) {
                        DropdownMenuItem(
                            modifier = Modifier.height(40.dp),
                            contentPadding = PaddingValues(5.dp),
                            onClick = { onItemClick(index) },
                        ) {
                            val date = remember { day.isoDateDay.toCalendar() }
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                text = "${
                                    date.get(Calendar.DAY_OF_WEEK).toDayOfWeek()
                                } ${date.toMonth()}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight(700),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = LocalColors.current.textPrimary,
                            )
                        }
                    }
                }
            }
        }
    }
}
