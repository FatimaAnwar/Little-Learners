package com.littlelearners.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.littlelearners.components.AppBackground

@Composable
fun ResultScreen(
    score: Int,
    onTryAgain: () -> Unit,
    onMainMenu: () -> Unit
) {

    AppBackground {

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(25.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            Card(
                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(35.dp),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 12.dp
                    ),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            Color.White.copy(
                                alpha = 0.95f
                            )
                    )
            ) {

                Column(
                    modifier =
                        Modifier.padding(30.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "🎉 Great Job! 🎉",
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(20.dp)
                    )

                    Text(
                        text = "$score / 5",
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text =
                            when {
                                score == 5 ->
                                    "Amazing! ⭐"

                                score >= 3 ->
                                    "Very Good! 🌟"

                                else ->
                                    "Good Try! 😊"
                            },

                        fontSize = 27.sp,
                        fontWeight = FontWeight.Bold,

                        textAlign =
                            TextAlign.Center
                    )

                    Spacer(
                        modifier =
                            Modifier.height(30.dp)
                    )

                    Button(
                        onClick = onTryAgain,

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(70.dp),

                        shape =
                            RoundedCornerShape(25.dp),

                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    Color(0xFF6C63FF)
                            )
                    ) {

                        Text(
                            text = "🔄 Try Again",
                            fontSize = 23.sp
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(15.dp)
                    )

                    Button(
                        onClick = onMainMenu,

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(70.dp),

                        shape =
                            RoundedCornerShape(25.dp),

                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    Color(0xFF53C878)
                            )
                    ) {

                        Text(
                            text = "🏠 Main Menu",
                            fontSize = 23.sp
                        )
                    }
                }
            }
        }
    }
}