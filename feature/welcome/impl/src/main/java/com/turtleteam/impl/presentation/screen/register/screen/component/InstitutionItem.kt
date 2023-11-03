package com.turtleteam.impl.presentation.screen.register.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.api.data.model.Institution
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas

@Composable
fun InstitutionItem(institution: Institution, onClick: (Institution) -> Unit) {

    Column(
        Modifier
            .height(40.dp)
            .width(130.dp)
            .border(1.dp, color = Color(0xFF417B65), shape = RoundedCornerShape(12.dp))
            .background(
                TurtleTheme.color.institutionBackgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable() { onClick(institution) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = institution.title, style = TextStyle(
                fontSize = 16.sp,
                fontFamily = fontQanelas,
                color = TurtleTheme.color.textColor
            )
        )
    }
}
