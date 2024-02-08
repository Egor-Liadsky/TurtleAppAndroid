package com.turtleteam.impl.presentation.feedback.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.core_view.theme.TurtleTheme
import com.turtleteam.core_view.theme.fontQanelas
import com.turtleteam.core_view.view.button.CommonButton
import com.turtleteam.core_view.view.topbar.CommonTopBar
import com.turtleteam.impl.presentation.feedback.viewModel.FeedbackViewModel

@Composable
fun FeedbackScreen(viewModel: FeedbackViewModel) {

    val state = viewModel.state.collectAsState()
    val context = LocalContext.current
    val emailIntent = Intent(Intent.ACTION_SENDTO)

    LaunchedEffect(key1 = Unit) {
        viewModel.getFeedbackEmail()
    }

    Column(Modifier.fillMaxSize()) {

        CommonTopBar(title = "Обратная связь") {
            viewModel.onBackButtonClick()
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "У вас есть вопрос,\nпредложение или жалоба?",
                style = TextStyle(
                    fontSize = 22.sp,
                    fontFamily = fontQanelas,
                    color = TurtleTheme.color.textPrimary,
                    textAlign = TextAlign.Center
                ),
            )

            CommonButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                title = "Написать"
            ) {
                emailIntent.data = Uri.parse("mailto:")
                emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(state.value.feedbackEmail))
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Обратная связь")
                context.startActivity(Intent.createChooser(emailIntent, "Обратная связь"))
            }
        }
    }
}