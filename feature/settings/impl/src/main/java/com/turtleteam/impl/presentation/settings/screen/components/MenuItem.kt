package com.turtleteam.impl.presentation.settings.screen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

data class MenuInfo(
    val title: String,
    val onClick: () -> Unit
)

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun MenuItem(menuInfo: MenuInfo) {
    Button(
        modifier = Modifier.fillMaxWidth().width(20.dp),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(0.dp),
        onClick = { menuInfo.onClick() },
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_square),
                contentDescription = "ic_square",
                modifier = Modifier.size(width = 18.dp, height = 20.dp),
                tint = Color(0xFFA8ACAF)
            )
            Text(
                text = menuInfo.title, style = TextStyle(
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = TurtleTheme.color.textPrimary
                ), modifier = Modifier.padding(start = 18.dp)
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_next), contentDescription = "ic_next",
                modifier = Modifier.size(11.dp),
                tint = TurtleTheme.color.primary
            )
        }
    }
}
