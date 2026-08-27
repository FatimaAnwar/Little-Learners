package com.littlelearners.components

import androidx.compose.runtime.Composable

@Composable
fun ConfettiPlaceholder(
    visible: Boolean
) {

    if (!visible) return

    /*
     * CONFETTI PLACEHOLDER
     *
     * Later we can replace this with:
     *
     * - Compose particle animation
     * - Lottie
     * - Custom Canvas confetti
     *
     * without changing the game logic.
     */
}