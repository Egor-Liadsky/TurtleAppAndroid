package com.turtleteam.core_view.view.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.theme.fontQanelas

@Composable
fun CommonTopBar(title: String, backButtonClick: (() -> Unit)? = null) {
    val white = Color.White.copy(0.25f)
    Column(
        Modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(colors = listOf(Color(0xFF488166), Color(0xFFA1C97A))),
                shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
            ),
    ) {
        Row(
            Modifier.padding(
                vertical = 20.dp,
                horizontal = 16.dp,
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (backButtonClick != null) {
                Button(
                    modifier = Modifier.size(40.dp),
                    onClick = { backButtonClick() },
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 0.dp
                    ),
                    colors = ButtonDefaults.buttonColors(backgroundColor = white),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(7.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        tint = Color.White
                    )
                }
            }
            Text(
                text = title, style = TextStyle(
                    color = Color.White,
                    fontSize = 22.sp,
                    fontFamily = fontQanelas
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}