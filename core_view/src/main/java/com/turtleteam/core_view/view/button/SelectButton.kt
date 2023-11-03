package com.turtleteam.core_view.view.button

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.theme.LocalColors
import com.turtleteam.core_view.theme.LocalShapes
import com.turtleteam.core_view.theme.LocalTheme
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas
import com.turtleteam.core_view.utils.SelectButtonIndicator

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun SelectButton(
    modifier: Modifier = Modifier,
    title: String,
    indicationColor: Color = Color.Transparent,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                onClick = onClick,
                interactionSource = MutableInteractionSource(),
                indication = SelectButtonIndicator(indicationColor, 12.dp)
            )
            .border(
                1.dp,
                if (!LocalTheme.current) LocalColors.current.textColor.copy(0.35F) else Color.Transparent,
                LocalShapes.current.medium
            ),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_turtle_select),
            contentDescription = null,
            tint = LocalColors.current.buttonSelectTurtle,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = fontQanelas,
                    color = TurtleTheme.color.selectTextColor,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}
