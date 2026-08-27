package com.littlelearners.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun AppBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    val gradient =
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFF9EDCFF),
                Color(0xFFFFE99A),
                Color(0xFFFFC7D9)
            )
        )

    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(gradient)
    ) {
        content()
    }
}