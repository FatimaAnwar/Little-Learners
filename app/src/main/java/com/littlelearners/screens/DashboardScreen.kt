package com.littlelearners.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.littlelearners.components.AppBackground
import com.littlelearners.components.BigButton

@Composable
fun DashboardScreen(
    musicEnabled: Boolean,
    onMusicChanged: (Boolean) -> Unit,
    onSizeGameClicked: () -> Unit
) {

    AppBackground {

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(24.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            Text(
                text = "🌈 Little Learners 🌈",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3E356B),
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Text(
                text = "Learn • Have Fun!",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier =
                    Modifier.height(35.dp)
            )

            Card(
                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(30.dp),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    ),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            Color.White.copy(alpha = 0.9f)
                    )
            ) {

                Column(
                    modifier =
                        Modifier.padding(22.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(18.dp)
                ) {

                    Text(
                        text = "Let's Learn!",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                    BigButton(
                        text = "Big, Bigger, Biggest",
                        icon = "🔵",
                        onClick = onSizeGameClicked
                    )

                    Button(
                        onClick = {
                            onMusicChanged(!musicEnabled)
                        },

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(70.dp),

                        shape =
                            RoundedCornerShape(25.dp),

                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    if (musicEnabled)
                                        Color(0xFF53C878)
                                    else
                                        Color(0xFF9E9E9E)
                            )
                    ) {

                        Text(
                            text =
                                if (musicEnabled)
                                    "🎵 Music ON"
                                else
                                    "🔇 Music OFF",

                            fontSize = 22.sp
                        )
                    }
                }
            }
        }
    }
}