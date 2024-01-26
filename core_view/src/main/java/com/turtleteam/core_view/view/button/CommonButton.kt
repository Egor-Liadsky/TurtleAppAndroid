package com.turtleteam.core_view.view.button

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    title: String,
    textColor: Color = Color.White,
    background: Color = TurtleTheme.color.textColor,
    fontSize: TextUnit = 22.sp,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(backgroundColor = background),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = fontSize,
                fontFamily = fontQanelas,
                color = textColor,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.padding(vertical = 8.dp),
        )
    }
}
