package com.littlelearners.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SizeObject(
    emoji: String,
    sizeDp: Int,
    selected: Boolean,
    correct: Boolean,
    wrong: Boolean,
    enabled: Boolean,
    onClick: () -> Unit
) {

    val targetScale =
        when {
            correct -> 1.18f
            wrong -> 0.92f
            selected -> 1.08f
            else -> 1f
        }

    val scale =
        animateFloatAsState(
            targetValue = targetScale,
            animationSpec =
                spring(
                    dampingRatio = 0.45f
                ),
            label = "objectScale"
        )

    Surface(
        modifier =
            Modifier
                .size(sizeDp.dp)
                .then(
                    Modifier
                        .clickable(
                            enabled = enabled,
                            onClick = onClick
                        )
                )
                .then(
                    Modifier
                        .size(sizeDp.dp)
                ),

        shape = CircleShape,

        border =
            BorderStroke(
                width = 5.dp,
                color =
                    if (selected)
                        Color(0xFF6C63FF)
                    else
                        Color.White
            ),

        shadowElevation = 10.dp,

        color = Color.White
    ) {

        Box(
            contentAlignment =
                Alignment.Center
        ) {

            Image(
                painter = painterResource(R.drawable.teddy),
                contentDescription = "Teddy Bear"
                )
        }
    }
}