package com.littlelearners.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Dashboard / Home Screen
 *
 * Main entry screen for Little Learners.
 *
 * Current concept:
 * Big, Bigger, Biggest
 *
 * Music control:
 * Located in the TOP-RIGHT corner.
 */
@Composable
fun DashboardScreen(
    musicEnabled: Boolean,
    onMusicChanged: (Boolean) -> Unit,
    onSizeConceptClick: () -> Unit
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // ---------------------------------------------------------
        // BACKGROUND
        // ---------------------------------------------------------
        //
        // Soft colorful gradient designed for toddlers.
        //
        // The gradient is bright but intentionally not too intense.
        //
        // ---------------------------------------------------------

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF9EDCFF),
                            Color(0xFFB8E8FF),
                            Color(0xFFFFE99A)
                        )
                    )
                )
        )

        // ---------------------------------------------------------
        // MAIN CONTENT
        // ---------------------------------------------------------

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 20.dp,
                    vertical = 16.dp
                ),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Top
        ) {

            // -----------------------------------------------------
            // HEADER
            // -----------------------------------------------------

            /*
             * Leave space on the right for the music button.
             *
             * The title remains visually centered.
             */
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 70.dp
                        ),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "🌈 Little Learners 🌈",

                        fontSize = 34.sp,

                        fontWeight =
                            FontWeight.Bold,

                        textAlign =
                            TextAlign.Center,

                        color =
                            Color(0xFF3F3D56)
                    )

                    Spacer(
                        modifier =
                            Modifier.height(4.dp)
                    )

                    Text(
                        text = "Learn • Have Fun!",

                        fontSize = 18.sp,

                        fontWeight =
                            FontWeight.Medium,

                        textAlign =
                            TextAlign.Center,

                        color =
                            Color(0xFF55536B)
                    )
                }

                // -------------------------------------------------
                // MUSIC TOGGLE - TOP RIGHT
                // -------------------------------------------------

                MusicToggleButton(
                    musicEnabled =
                        musicEnabled,

                    onMusicChanged =
                        onMusicChanged,

                    modifier =
                        Modifier.align(
                            Alignment.TopEnd
                        )
                )
            }

            Spacer(
                modifier =
                    Modifier.height(45.dp)
            )

            // -----------------------------------------------------
            // WELCOME MESSAGE
            // -----------------------------------------------------

            Text(
                text = "Let's Learn About Size! 🌟",

                fontSize = 26.sp,

                fontWeight =
                    FontWeight.Bold,

                textAlign =
                    TextAlign.Center,

                color =
                    Color(0xFF3F3D56)
            )

            Spacer(
                modifier =
                    Modifier.height(25.dp)
            )

            // -----------------------------------------------------
            // SIZE CONCEPT CARD
            // -----------------------------------------------------

            SizeConceptCard(
                onClick =
                    onSizeConceptClick
            )

            Spacer(
                modifier =
                    Modifier.height(25.dp)
            )

            Text(
                text =
                    "Tap the card to start learning!",

                fontSize = 17.sp,

                fontWeight =
                    FontWeight.Medium,

                textAlign =
                    TextAlign.Center,

                color =
                    Color(0xFF55536B)
            )
        }
    }
}

/**
 * Music ON/OFF button.
 *
 * This button is deliberately large and simple
 * so a 3-year-old can easily tap it.
 */
@Composable
private fun MusicToggleButton(
    musicEnabled: Boolean,
    onMusicChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    Surface(
        modifier = modifier
            .size(70.dp)
            .clickable {

                onMusicChanged(
                    !musicEnabled
                )
            },

        shape =
            RoundedCornerShape(20.dp),

        color =
            Color.White,

        shadowElevation =
            8.dp,

        border =
            BorderStroke(
                width = 3.dp,
                color =
                    Color(0xFFFFFFFF)
            )
    ) {

        Box(
            contentAlignment =
                Alignment.Center
        ) {

            Text(
                text =
                    if (musicEnabled) {
                        "🎵"
                    } else {
                        "🔇"
                    },

                fontSize = 32.sp
            )
        }
    }
}

/**
 * Main learning card for:
 *
 * Big
 * Bigger
 * Biggest
 */
@Composable
private fun SizeConceptCard(
    onClick: () -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clickable {
                onClick()
            },

        shape =
            RoundedCornerShape(35.dp),

        color =
            Color.White.copy(
                alpha = 0.95f
            ),

        shadowElevation =
            12.dp,

        border =
            BorderStroke(
                width = 5.dp,
                color =
                    Color(0xFF7B68EE)
            )
    ) {

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(20.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            // -----------------------------------------------------
            // VISUAL REPRESENTATION
            // -----------------------------------------------------

            Text(
                text = "🍎",

                fontSize = 50.sp
            )

            Spacer(
                modifier =
                    Modifier.height(4.dp)
            )

            Text(
                text = "🍎",

                fontSize = 75.sp
            )

            Spacer(
                modifier =
                    Modifier.height(4.dp)
            )

            Text(
                text = "🍎",

                fontSize = 100.sp
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            // -----------------------------------------------------
            // TITLE
            // -----------------------------------------------------

            Text(
                text =
                    "Big, Bigger, Biggest",

                fontSize = 27.sp,

                fontWeight =
                    FontWeight.Bold,

                textAlign =
                    TextAlign.Center,

                color =
                    Color(0xFF3F3D56)
            )

            Spacer(
                modifier =
                    Modifier.height(5.dp)
            )

            Text(
                text =
                    "🔵 Learn about sizes!",

                fontSize = 17.sp,

                fontWeight =
                    FontWeight.Medium,

                color =
                    Color(0xFF666477),

                textAlign =
                    TextAlign.Center
            )
        }
    }
}