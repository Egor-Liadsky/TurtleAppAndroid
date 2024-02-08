package com.turtleteam.core_view.view.textField

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.R
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    @DrawableRes trailingIcon: Int? = null,
    value: String,
    onClearValueClick: () -> Unit,
    onValueChange: (String) -> Unit,
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontFamily = fontQanelas,
            color = TurtleTheme.color.textPrimary,
        ),
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        singleLine = true,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, TurtleTheme.color.stroke, RoundedCornerShape(12.dp))
                    .background(TurtleTheme.color.blocks)
                    .padding(horizontal = 20.dp, vertical = 13.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = fontQanelas,
                                color = TurtleTheme.color.textAccent,
                            ),
                        )
                    }
                    innerTextField()
                }
                if (trailingIcon != null) {
                    if (value != "") {
                        IconButton(
                            modifier = Modifier.size(24.dp),
                            onClick = { onClearValueClick() }
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = "",
                                tint = TurtleTheme.color.accent,
                            )
                        }
                    } else {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = trailingIcon),
                            contentDescription = "",
                            tint = TurtleTheme.color.accent,
                        )
                    }
                }
            }
        },
    )
}
