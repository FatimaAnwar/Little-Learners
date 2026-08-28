package com.littlelearners

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.littlelearners.audio.AudioManager
import com.littlelearners.navigation.AppNavigation
import com.littlelearners.ui.LittleLearnersTheme

class MainActivity : ComponentActivity() {

    private lateinit var audioManager: AudioManager

    /*
     * Current music preference.
     *
     * true  = Music ON
     * false = Music OFF
     */
    private var musicEnabled: Boolean = true

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        /*
         * Create one AudioManager for the Activity.
         */
        audioManager = AudioManager(this)

        setContent {

            LittleLearnersTheme {

                /*
                 * Compose state for the Music button.
                 */
                var musicState by remember {
                    mutableStateOf(musicEnabled)
                }

                /*
                 * Main application navigation.
                 */
                AppNavigation(
                    audioManager = audioManager,
                    musicEnabled = musicState,

                    onMusicChanged = { enabled ->

                        /*
                         * Store the current music state.
                         */
                        musicEnabled = enabled

                        /*
                         * Update the Compose UI.
                         */
                        musicState = enabled

                        /*
                         * Start or stop background music.
                         */
                        if (enabled) {
                            audioManager.playSoftBackgroundMusic()
                        } else {
                            audioManager.stopBackgroundMusic()
                        }
                    }
                )
            }
        }
    }

    /*
     * Called when the application leaves
     * the foreground.
     *
     * Music stops when:
     *
     * - Home button is pressed
     * - Another app is opened
     * - Screen is locked
     * - App is put into the background
     */
    override fun onPause() {

        audioManager.stopBackgroundMusic()

        super.onPause()
    }

    /*
     * Called when the application returns
     * to the foreground.
     *
     * Music resumes only if Music was ON.
     */
    override fun onResume() {

        super.onResume()

        if (musicEnabled) {
            audioManager.playSoftBackgroundMusic()
        }
    }

    /*
     * Release all audio resources when
     * the Activity is destroyed.
     */
    override fun onDestroy() {

        audioManager.release()

        super.onDestroy()
    }
}
