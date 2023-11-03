package com.turtleteam.impl.presentation.screen.register.screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.api.data.model.Institution
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas
import com.turtleteam.core_view.utils.SelectButtonIndicator

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun InstitutionItem(
    institution: Institution,
    selectInstitution: Institution?,
    onClick: () -> Unit
) {
    val isSelectedInstitution = selectInstitution == institution
    Column(
        Modifier
            .height(40.dp)
            .width(130.dp)
            .border(
                1.dp,
                color = if (isSelectedInstitution) Color(0xFF417B65)
                else Color(0x417B6559),
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                TurtleTheme.color.institutionBackgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                onClick = onClick,
                interactionSource = MutableInteractionSource(),
                indication = SelectButtonIndicator(TurtleTheme.color.textColor, 12.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = institution.title, style = TextStyle(
                fontSize = 16.sp,
                fontFamily = fontQanelas,
                color = if (isSelectedInstitution) TurtleTheme.color.textColor else Color(0x417B6559)
            )
        )
    }
}
