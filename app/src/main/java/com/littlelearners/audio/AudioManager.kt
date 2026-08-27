package com.littlelearners.audio

import android.content.Context
import android.media.MediaPlayer

import com.littlelearners.R

class AudioManager(
    private val context: Context
) {

    private var backgroundPlayer: MediaPlayer? = null

    private var effectPlayer: MediaPlayer? = null

    fun playSoftBackgroundMusic() {

    stopBackgroundMusic()

    backgroundPlayer =
        MediaPlayer.create(
            context,
            R.raw.background_music
        )

    backgroundPlayer?.isLooping = true

    backgroundPlayer?.setVolume(
        0.15f,
        0.15f
    )

    backgroundPlayer?.start()
}

    fun stopBackgroundMusic() {
        backgroundPlayer?.stop()
        backgroundPlayer?.release()
        backgroundPlayer = null
    }

    fun playAudioInstruction(promptType: String) {

        /*
         * Example:
         *
         * BIG     -> "Find the big item"
         * BIGGER  -> "Find the bigger item"
         * BIGGEST -> "Tap the biggest item"
         *
         * Add your recorded MP3 files to:
         *
         * app/src/main/res/raw/
         */

        // Placeholder intentionally left safe for initial build.
    }

    fun playCorrectSound() {

    effectPlayer?.release()

    effectPlayer =
        MediaPlayer.create(
            context,
            R.raw.correct
        )

    effectPlayer?.setOnCompletionListener {
        it.release()
    }

    effectPlayer?.start()
}

    fun playWrongSound() {

    effectPlayer?.release()

    effectPlayer =
        MediaPlayer.create(
            context,
            R.raw.wrong
        )

    effectPlayer?.setOnCompletionListener {
        it.release()
    }

    effectPlayer?.start()
}


    fun release() {
        stopBackgroundMusic()

        effectPlayer?.release()
        effectPlayer = null
    }
}