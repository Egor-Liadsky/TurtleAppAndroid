package com.turtleteam.core_view.view.frame

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.model.Day
import com.turtleteam.core_view.theme.LocalColors
import com.turtleteam.core_view.utils.toCalendar
import com.turtleteam.core_view.utils.toDayOfWeek
import com.turtleteam.core_view.utils.toMonth
import java.util.Calendar

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun DateItem(day: Day, onClick: () -> Unit) {
    val date = remember { day.isoDateDay.toCalendar() }
    Row(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 2.dp)
            .height(31.dp)
            .background(LocalColors.current.dateBackground, RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFF417B65).copy(0.35f), RoundedCornerShape(12.dp))
            .clickable(MutableInteractionSource(), indication = rememberRipple(), onClick = onClick)
            .padding(horizontal = 7.dp),
        horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = "",
            tint = LocalColors.current.textColor,
        )
        Text(
            text = "${date.get(Calendar.DAY_OF_WEEK).toDayOfWeek()} ${date.toMonth()}",
            fontSize = 20.sp,
            fontWeight = FontWeight(700),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = LocalColors.current.textColor,
        )
        Icon(
            modifier = Modifier.rotate(180F),
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = "",
            tint = LocalColors.current.textColor,
        )
    }
}
