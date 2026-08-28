package com.littlelearners.audio

import android.content.Context
import android.media.MediaPlayer
import com.littlelearners.R

class AudioManager(
    private val context: Context
) {

    private var backgroundPlayer: MediaPlayer? = null
    private var effectPlayer: MediaPlayer? = null

    /**
     * Plays soft background music continuously.
     */
    fun playSoftBackgroundMusic() {

        // Don't start another player if music is
        // already playing.
        if (backgroundPlayer?.isPlaying == true) {
            return
        }

        stopBackgroundMusic()

        backgroundPlayer =
            MediaPlayer.create(
                context,
                R.raw.background_music
            )

        backgroundPlayer?.apply {

            isLooping = true

            // Keep the background music quiet
            // so the child can hear instructions.
            setVolume(
                0.75f,
                0.75f
            )

            start()
        }
    }

    /**
     * Completely stops background music.
     */
    fun stopBackgroundMusic() {

        backgroundPlayer?.let { player ->

            if (player.isPlaying) {
                player.stop()
            }

            player.release()
        }

        backgroundPlayer = null
    }

    /**
     * Plays the instruction for the current question.
     *
     * BIG     -> big.mp3
     * BIGGER  -> bigger.mp3
     * BIGGEST -> biggest.mp3
     */
    fun playAudioInstruction(
        promptType: String
    ) {

        stopEffect()

        val audioResource =
            when (promptType.lowercase()) {

                "big" ->
                    R.raw.big

                "bigger" ->
                    R.raw.bigger

                "biggest" ->
                    R.raw.biggest

                else ->
                    return
            }

        effectPlayer =
            MediaPlayer.create(
                context,
                audioResource
            )

        effectPlayer?.apply {

            setVolume(
                1.0f,
                1.0f
            )

            setOnCompletionListener { player ->

                player.release()

                if (effectPlayer == player) {
                    effectPlayer = null
                }
            }

            start()
        }
    }

    /**
     * Plays the correct-answer sound.
     */
    fun playCorrectSound() {

        playEffect(
            R.raw.correct
        )
    }

    /**
     * Plays the wrong-answer sound.
     */
    fun playWrongSound() {

        playEffect(
            R.raw.wrong
        )
    }

    /**
     * Generic sound-effect player.
     */
    private fun playEffect(
        resourceId: Int
    ) {

        stopEffect()

        effectPlayer =
            MediaPlayer.create(
                context,
                resourceId
            )

        effectPlayer?.apply {

            setVolume(
                1.0f,
                1.0f
            )

            setOnCompletionListener { player ->

                player.release()

                if (effectPlayer == player) {
                    effectPlayer = null
                }
            }

            start()
        }
    }

    /**
     * Stops the currently playing instruction/effect.
     */
    private fun stopEffect() {

        effectPlayer?.let { player ->

            if (player.isPlaying) {
                player.stop()
            }

            player.release()
        }

        effectPlayer = null
    }

    /**
     * Release all MediaPlayer resources.
     */
    fun release() {

        stopBackgroundMusic()
        stopEffect()
    }
}