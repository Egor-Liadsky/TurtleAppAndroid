package com.turtleteam.impl.presentation.presentation.additional.screen.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.turtleteam.api.models.Ring

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RingList(
    rings: Ring,
) {
    val listState = rememberLazyListState(1)
    val flingBehavior = rememberSnapFlingBehavior(listState)
    val alpha = remember { Animatable(0F) }
    val paddings = with(LocalDensity.current) { 8.dp.toPx() }

    LazyRow(
        modifier = Modifier.alpha(alpha.value).padding(top = 16.dp),
        state = listState,
        flingBehavior = flingBehavior,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(items = rings.ringName ?: listOf()) { ring ->
            rings.value?.forEach { ringValue ->
                if (ringValue.type == ring.name) {
                    RingItem(name = ring.value ?: "", rings = ringValue.rings ?: listOf())
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        val itemInfo = listState.layoutInfo.visibleItemsInfo.firstOrNull { it.index == 1 }
        if (itemInfo != null) {
            val center = listState.layoutInfo.viewportEndOffset / 2
            val childCenter = itemInfo.offset + itemInfo.size / 2
            listState.scrollBy((childCenter - center + paddings))
        } else {
            listState.scrollToItem(1)
        }
        alpha.animateTo(1f, tween(durationMillis = 350, easing = FastOutSlowInEasing))
    })
}
