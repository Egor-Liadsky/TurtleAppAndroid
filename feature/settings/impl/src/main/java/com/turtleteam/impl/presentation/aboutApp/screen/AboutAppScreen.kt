package com.turtleteam.impl.presentation.aboutApp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas
import com.turtleteam.core_view.view.topbar.CommonTopBar
import com.turtleteam.impl.presentation.aboutApp.viewModel.AboutAppViewModel

@Composable
fun AboutAppScreen(viewModel: AboutAppViewModel) {

    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getVersionName()
    }

    Column(modifier = Modifier.fillMaxSize()) {

        CommonTopBar(title = "О приложении") {
            viewModel.onBackButtonClick()
        }

        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_turtle_logo),
                contentDescription = null,
                modifier = Modifier.size(136.dp, 87.dp)
            )

            Text(
                text = "Turtle Schedule",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = fontQanelas,
                    color = TurtleTheme.color.textColor,
                ),
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = state.value.versionName.toString(),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = fontQanelas,
                    color = TurtleTheme.color.textColor,
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}