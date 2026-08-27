package com.littlelearners.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LittleLearnersColors =
    lightColorScheme()

@Composable
fun LittleLearnersTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = LittleLearnersColors,
        content = content
    )
}