package com.turtleteam.impl.presentation.onBoarding.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas
import com.turtleteam.impl.presentation.presentation.onBoarding.screen.OnBoarding

@Composable
fun OnBoardingItem(onBoarding: OnBoarding) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = onBoarding.icon),
            contentDescription = null,
            modifier = Modifier.size(200.dp),
        )
        Text(
            text = onBoarding.title,
            style = TextStyle(
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontFamily = fontQanelas,
                color = TurtleTheme.color.textPrimary,
            ),
            modifier = Modifier.padding(top = 20.dp),
        )
    }
}
