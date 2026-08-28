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

@Composable
fun SizeGameScreen(
    viewModel: SizeGameViewModel,
    onBack: () -> Unit,
    onFinished: (Int) -> Unit
) {

    val state by viewModel.state.collectAsState()

    // ---------------------------------------------------------
    // HANDLE ANSWER
    // ---------------------------------------------------------
    //
    // When the child selects an answer:
    //
    // Correct -> show feedback for 2 seconds
    // Wrong   -> show feedback briefly
    //
    // Then move to the next question.
    //
    // ---------------------------------------------------------

    LaunchedEffect(
        state.selectedIndex,
        state.questionNumber
    ) {

        if (state.selectedIndex != null) {

            if (state.isCorrect) {

                // Give the child time to see
                // the success animation.
                delay(2000)

            } else {

                // Short feedback for wrong answer.
                delay(800)
            }

            viewModel.nextQuestion()
        }
    }

    // ---------------------------------------------------------
    // HANDLE GAME FINISHED
    // ---------------------------------------------------------
    //
    // This is intentionally a SEPARATE effect.
    //
    // When question 5 is completed, the ViewModel changes:
    //
    // gameFinished = true
    //
    // This effect detects that change and opens
    // the Result screen.
    //
    // ---------------------------------------------------------

    LaunchedEffect(state.gameFinished) {

        if (state.gameFinished) {

            // Small delay gives Compose time to render
            // the final answer state before navigation.
            delay(100)

            onFinished(state.score)
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

            // -------------------------------------------------
            // TOP BAR
            // -------------------------------------------------

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

                    fontWeight =
                        FontWeight.Bold
                )
            }

            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )

            // -------------------------------------------------
            // QUESTION
            // -------------------------------------------------

            Text(
                text =
                    when (state.question) {

                        QuestionType.BIG ->
                            "👆 Find the BIG item!"

                        QuestionType.BIGGER ->
                            "👆 Find the BIGGER item!"

                        QuestionType.BIGGEST ->
                            "👆 Tap the BIGGEST item!"
                    },

                modifier =
                    Modifier.fillMaxWidth(),

                fontSize = 31.sp,

                fontWeight =
                    FontWeight.Bold,

                textAlign =
                    TextAlign.Center
            )

            Spacer(
                modifier =
                    Modifier.height(35.dp)
            )

            // -------------------------------------------------
            // SAME OBJECT - THREE DIFFERENT SIZES
            // -------------------------------------------------

            Row(
                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceEvenly,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                // BIG
                // 100dp

                SizeObject(
                    emoji =
                        state.objectEmoji,

                    sizeDp = 100,

                    selected =
                        state.selectedIndex == 0,

                    correct =
                        state.selectedIndex == 0 &&
                                state.isCorrect,

                    wrong =
                        state.selectedIndex == 0 &&
                                !state.isCorrect,

                    enabled =
                        state.selectedIndex == null,

                    onClick = {
                        viewModel.selectObject(0)
                    }
                )

                // BIGGER
                // 150dp

                SizeObject(
                    emoji =
                        state.objectEmoji,

                    sizeDp = 150,

                    selected =
                        state.selectedIndex == 1,

                    correct =
                        state.selectedIndex == 1 &&
                                state.isCorrect,

                    wrong =
                        state.selectedIndex == 1 &&
                                !state.isCorrect,

                    enabled =
                        state.selectedIndex == null,

                    onClick = {
                        viewModel.selectObject(1)
                    }
                )

                // BIGGEST
                // 200dp

                SizeObject(
                    emoji =
                        state.objectEmoji,

                    sizeDp = 200,

                    selected =
                        state.selectedIndex == 2,

                    correct =
                        state.selectedIndex == 2 &&
                                state.isCorrect,

                    wrong =
                        state.selectedIndex == 2 &&
                                !state.isCorrect,

                    enabled =
                        state.selectedIndex == null,

                    onClick = {
                        viewModel.selectObject(2)
                    }
                )
            }

            Spacer(
                modifier =
                    Modifier.height(35.dp)
            )

            // -------------------------------------------------
            // CORRECT FEEDBACK
            // -------------------------------------------------

            AnimatedVisibility(
                visible =
                    state.showSuccessAnimation,

                enter =
                    scaleIn()
            ) {

                Text(
                    text =
                        "🎉 Well Done! 🎉",

                    fontSize = 32.sp,

                    fontWeight =
                        FontWeight.Bold,

                    textAlign =
                        TextAlign.Center
                )
            }

            // -------------------------------------------------
            // WRONG FEEDBACK
            // -------------------------------------------------

            AnimatedVisibility(
                visible =
                    state.showWrongAnimation
            ) {

                Text(
                    text =
                        "😊 Let's try another one!",

                    fontSize = 25.sp,

                    textAlign =
                        TextAlign.Center
                )
            }
        }
    }
}