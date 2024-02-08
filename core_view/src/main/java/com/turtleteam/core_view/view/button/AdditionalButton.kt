package com.turtleteam.core_view.view.button

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun AdditionalButton(
    modifier: Modifier = Modifier,
    title: String,
    isArrowDown: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = TurtleTheme.color.additionalButtonBackground),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        border = BorderStroke(1.dp, TurtleTheme.color.additionalButtonStroke),
        contentPadding = PaddingValues(0.dp),
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = fontQanelas,
                    color = TurtleTheme.color.textPrimary,
                    textAlign = TextAlign.Center,
                ),
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_next), contentDescription = null,
                tint = TurtleTheme.color.additionalButtonArrow,
                modifier = Modifier.size(16.dp)
                    .rotate(if (isArrowDown) 90f else 0f)
            )
        }
    }
}
