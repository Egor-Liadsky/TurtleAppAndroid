package com.turtleteam.core_view.view.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

@Composable
fun SelectGroupButton(
    modifier: Modifier = Modifier,
    title: String,
    onButtonClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        onClick = { onButtonClick() },
        border = BorderStroke(
            1.dp,
            TurtleTheme.color.selectGroupButtonStroke,
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(backgroundColor = TurtleTheme.color.selectGroupButtonBackground),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(0.dp),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(TurtleTheme.color.selectGroupButtonBackground)) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontFamily = fontQanelas,
                        color = TurtleTheme.color.textPrimary,
                        textAlign = TextAlign.Center,
                    ),
                )
            }
            Column(
                Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_next),
                    contentDescription = null,
                    tint = TurtleTheme.color.selectGroupButtonArrow,
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}
