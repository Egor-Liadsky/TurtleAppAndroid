package com.turtleteam.core_view.view.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

@Composable
fun StageBar(modifier: Modifier = Modifier, number: Int, count: Int) {
    val state = rememberLazyListState()

    val width = with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.toDp() }

    LazyRow(
        state = state,
        userScrollEnabled = false,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        contentPadding = PaddingValues(horizontal = (width)),
        horizontalArrangement = Arrangement.spacedBy(3.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(count) { iteration ->
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp),
                ) {
                    Text(
                        text = (iteration + 1).toString(),
                        modifier = Modifier
                            .background(
                                color = if (number == iteration + 1) TurtleTheme.color.stageBarSelectBackgroundColor else TurtleTheme.color.stageBarUnselectBackgroundColor,
                                shape = CircleShape,
                            )
                            .size(25.dp),
                        style = TextStyle(
                            color = if (number == iteration + 1) TurtleTheme.color.stageBarSelectTextColor else TurtleTheme.color.stageBarUnselectTextColor,
                            fontSize = 18.sp,
                            fontFamily = fontQanelas,
                            textAlign = TextAlign.Center,
                        ),
                    )
                    if (iteration + 1 != count) {
                        Spacer(
                            modifier = Modifier
                                .size(16.dp, 1.dp)
                                .background(
                                    if (number == iteration + 1) TurtleTheme.color.stageBarSelectBackgroundColor else TurtleTheme.color.stageBarUnselectBackgroundColor,
                                    RoundedCornerShape(5.dp),
                                ),
                        )
                    }
                }
            }
        }
    }
}
