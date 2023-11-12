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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.theme.fontQanelas

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    @DrawableRes
    trailingIcon: Int? = null,
    value: String,
    onValueChange: (String) -> Unit,
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontFamily = fontQanelas,
            color = Color(0xFF417B65),
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
                    .border(1.dp, Color(0xFFB6CCBB), RoundedCornerShape(12.dp))
                    .background(Color(0xFFF3F7E8))
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
                                color = Color(0xFF9E9C9F),
                            ),
                        )
                    }
                    innerTextField()
                }
                if (trailingIcon != null) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = trailingIcon),
                        contentDescription = "",
                        tint = Color(0xFF9E9C9F),
                    )
                }
            }
        },
    )
}
