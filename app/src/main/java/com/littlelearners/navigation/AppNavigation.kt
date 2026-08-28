package com.littlelearners.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.littlelearners.screens.DashboardScreen
import com.littlelearners.screens.ResultScreen
import com.littlelearners.screens.SizeGameScreen
import com.littlelearners.screens.SizeGameViewModel
import com.littlelearners.audio.AudioManager

enum class AppScreen {
    DASHBOARD,
    SIZE_GAME,
    RESULT
}

@Composable
fun AppNavigation(
    musicEnabled: Boolean,
    onMusicChanged: (Boolean) -> Unit,
    audioManager: AudioManager,
) {

    var currentScreen by remember {
        mutableStateOf(
            AppScreen.DASHBOARD
        )
    }

    var finalScore by remember {
        mutableStateOf(0)
    }

    val gameViewModel =
        remember {
            SizeGameViewModel()
        }

    when (currentScreen) {

        AppScreen.DASHBOARD -> {

            DashboardScreen(
                musicEnabled = musicEnabled,

                onMusicChanged =
                    onMusicChanged,

                onSizeConceptClick = {
                    gameViewModel.restart()

                    currentScreen =
                        AppScreen.SIZE_GAME
                }
            )
        }

        AppScreen.SIZE_GAME -> {

    SizeGameScreen(
        viewModel = gameViewModel,

        audioManager = audioManager,

        onBack = {
            currentScreen =
                AppScreen.DASHBOARD
        },

        onFinished = { score ->

            finalScore = score

            currentScreen =
                AppScreen.RESULT
        }
    )
}

        AppScreen.RESULT -> {

            ResultScreen(
                score = finalScore,

                onTryAgain = {

                    gameViewModel.restart()

                    currentScreen =
                        AppScreen.SIZE_GAME
                },

                onMainMenu = {

                    currentScreen =
                        AppScreen.DASHBOARD
                }
            )
        }
    }
}