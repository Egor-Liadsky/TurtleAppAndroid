package com.turtleteam.core_view.view.layout

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

@Composable
fun EmptyLayout(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int? = null,
    title: String,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        image?.let {
            Image(painter = painterResource(id = image), contentDescription = null)
        }
        Text(
            text = title,
            modifier = Modifier.padding(top = 20.dp),
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = fontQanelas,
                fontWeight = FontWeight.Bold,
                color = TurtleTheme.color.textColor,
                textAlign = TextAlign.Center
            )
        )
    }
}
