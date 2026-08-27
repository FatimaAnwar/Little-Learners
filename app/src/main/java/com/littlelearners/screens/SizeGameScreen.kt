package com.littlelearners.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.littlelearners.components.AppBackground
import com.littlelearners.components.SizeObject
import com.littlelearners.model.QuestionType
import kotlinx.coroutines.delay
import com.littlelearners.components.ConfettiPlaceholder

@Composable
fun SizeGameScreen(
    viewModel: SizeGameViewModel,
    onBack: () -> Unit,
    onFinished: (Int) -> Unit
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(
        state.selectedIndex,
        state.questionNumber
    ) {

        if (state.selectedIndex != null) {

            delay(
                if (state.isCorrect)
                    2000
                else
                    800
            )

            if (state.gameFinished) {
                onFinished(state.score)
            } else {
                viewModel.nextQuestion()
            }
        }
    }

    AppBackground {

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 18.dp,
                        vertical = 12.dp
                    ),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Box(
                modifier =
                    Modifier.fillMaxWidth()
            ) {

                TextButton(
                    onClick = onBack,
                    modifier =
                        Modifier.align(
                            Alignment.CenterStart
                        )
                ) {
                    Text(
                        text = "← Back",
                        fontSize = 20.sp
                    )
                }

                Text(
                    text =
                        "${state.questionNumber} / 5",

                    modifier =
                        Modifier.align(
                            Alignment.CenterEnd
                        ),

                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )

            Text(
                text =
                    when (state.question) {

                        QuestionType.BIG ->
                            "👆 Tap the BIG item!"

                        QuestionType.BIGGER ->
                            "👆 Tap the BIGGER item!"

                        QuestionType.BIGGEST ->
                            "👆 Tap the BIGGEST item!"
                    },

                fontSize = 31.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )

            Row(
                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceEvenly,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                state.objects.forEachIndexed { index, emoji ->

                    val size =
                        when (index) {
                            0 -> 100
                            1 -> 150
                            else -> 200
                        }

                    SizeObject(
                        emoji = emoji,
                        sizeDp = size,

                        selected =
                            state.selectedIndex == index,

                        correct =
                            state.selectedIndex == index &&
                                    state.isCorrect,

                        wrong =
                            state.selectedIndex == index &&
                                    !state.isCorrect,

                        enabled =
                            state.selectedIndex == null,

                        onClick = {
                            viewModel.selectObject(index)
                        }
                    )

                    if (index < 2) {
                        Spacer(
                            modifier =
                                Modifier.width(4.dp)
                        )
                    }
                }
            }

            // ⭐ ADD CONFETTI HERE
            ConfettiPlaceholder(
                visible = state.showSuccessAnimation
                )


            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )

            AnimatedVisibility(
                visible =
                    state.showSuccessAnimation,

                enter =
                    scaleIn()
            ) {

                Text(
                    text = "🎉 Well Done! 🎉",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            AnimatedVisibility(
                visible =
                    state.showWrongAnimation
            ) {

                Text(
                    text = "😊 Let's try another one!",
                    fontSize = 25.sp
                )
            }
        }
    }
}