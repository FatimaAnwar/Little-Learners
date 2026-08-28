package com.littlelearners

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.littlelearners.audio.AudioManager
import com.littlelearners.navigation.AppNavigation
import com.littlelearners.ui.LittleLearnersTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(
            savedInstanceState
        )

        setContent {

            LittleLearnersTheme {

                val audioManager =
                    remember {
                        AudioManager(
                            this@MainActivity
                        )
                    }

                var musicEnabled by remember {
                    mutableStateOf(true)
                }

                DisposableEffect(Unit) {

                    audioManager
                        .playSoftBackgroundMusic()

                    onDispose {

                        audioManager.release()
                    }
                }

                AppNavigation(

                    audioManager =
                        audioManager,

                    musicEnabled =
                        musicEnabled,

                    onMusicChanged = { enabled ->

                        musicEnabled =
                            enabled

                        if (enabled) {

                            audioManager
                                .playSoftBackgroundMusic()

                        } else {

                            audioManager
                                .stopBackgroundMusic()
                        }
                    }
                )
            }
        }
    }
}