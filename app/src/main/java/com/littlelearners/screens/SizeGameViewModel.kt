package com.littlelearners.screens

import androidx.lifecycle.ViewModel
import com.littlelearners.data.GameData
import com.littlelearners.model.QuestionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

data class SizeGameState(
     val questionNumber: Int = 1,
    val score: Int = 0,

    val objectEmoji: String = "🧸",

    val question: QuestionType =
        QuestionType.BIG,

    val selectedIndex: Int? = null,

    val isCorrect: Boolean = false,

    val showSuccessAnimation: Boolean = false,

    val showWrongAnimation: Boolean = false,

    val gameFinished: Boolean = false
)

class SizeGameViewModel : ViewModel() {

    private val _state =
        MutableStateFlow(
            SizeGameState()
        )

    val state: StateFlow<SizeGameState> =
        _state.asStateFlow()

    init {
        createQuestion()
    }

    private fun createQuestion() {

        /*
         * Select ONE object.
         *
         * All three displayed objects in this
         * question will use exactly the same object.
         */
        val selectedObject =
            GameData.objects.random()

        /*
         * Randomly ask the child to find:
         *
         * BIG
         * BIGGER
         * BIGGEST
         */
        val question =
            QuestionType.entries.random()

        _state.value =
            _state.value.copy(

                objectEmoji =
                    selectedObject.emoji,

                question =
                    question,

                selectedIndex = null,

                isCorrect = false,

                showSuccessAnimation = false,

                showWrongAnimation = false,

                gameFinished = false
            )
    }

    fun selectObject(index: Int) {

        /*
         * Prevent multiple taps while the
         * question is being processed.
         */
        if (_state.value.selectedIndex != null) {
            return
        }

        /*
         * The three positions always represent:
         *
         * 0 = BIG
         * 1 = BIGGER
         * 2 = BIGGEST
         */
        val correctIndex =
            when (_state.value.question) {

                QuestionType.BIG ->
                    0

                QuestionType.BIGGER ->
                    1

                QuestionType.BIGGEST ->
                    2
            }

        val correct =
            index == correctIndex

        _state.value =
            _state.value.copy(

                selectedIndex =
                    index,

                isCorrect =
                    correct,

                score =
                    if (correct)
                        _state.value.score + 1
                    else
                        _state.value.score,

                showSuccessAnimation =
                    correct,

                showWrongAnimation =
                    !correct
            )
    }

    fun nextQuestion() {

    /*
     * Question 5 has already been answered.
     *
     * Don't create question 6.
     */
    if (_state.value.questionNumber >= 5) {

        _state.value =
            _state.value.copy(
                gameFinished = true
            )

        return
    }

    /*
     * Move to the next question.
     */
    _state.value =
        _state.value.copy(
            questionNumber =
                _state.value.questionNumber + 1
        )

    /*
     * Generate:
     *
     * - a new object
     * - a new question
     */
    createQuestion()
}

    fun restart() {

        _state.value =
            SizeGameState(
                questionNumber = 1,
                score = 0
            )

        createQuestion()
    }
}