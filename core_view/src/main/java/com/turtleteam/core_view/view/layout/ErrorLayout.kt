package com.turtleteam.core_view.view.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

@Composable
fun ErrorLayout(onClick: () -> Unit) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = TurtleTheme.images.error),
            contentDescription = null,
            Modifier.size(117.dp)
        )
        Text(
            text = "Что-то пошло не так!",
            modifier = Modifier.padding(top = 20.dp),
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = fontQanelas,
                fontWeight = FontWeight.Bold,
                color = TurtleTheme.color.textPrimary
            )
        )
        TextButton(
            modifier = Modifier.padding(top = 4.dp),
            onClick = { onClick() },
        ) {
            Text(
                text = "Обновить",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.Bold,
                    color = TurtleTheme.color.textAccent
                )
            )
        }
    }
}
