package com.littlelearners.screens

import androidx.lifecycle.ViewModel
import com.littlelearners.data.GameData
import com.littlelearners.model.Question
import com.littlelearners.model.QuestionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

data class SizeGameState(
    val questionNumber: Int = 1,
    val score: Int = 0,
    val objects: List<String> = emptyList(),
    val question: QuestionType = QuestionType.BIG,
    val selectedIndex: Int? = null,
    val isCorrect: Boolean = false,
    val showSuccessAnimation: Boolean = false,
    val showWrongAnimation: Boolean = false,
    val gameFinished: Boolean = false
)

class SizeGameViewModel : ViewModel() {

    private val _state =
        MutableStateFlow(SizeGameState())

    val state: StateFlow<SizeGameState> =
        _state.asStateFlow()

    init {
        createQuestion()
    }

    private fun createQuestion() {

        val shuffled =
            GameData.objects
                .shuffled()
                .take(3)

        val question =
            QuestionType.entries.random()

        _state.value =
            _state.value.copy(
                objects = shuffled.map { it.emoji },
                question = question,
                selectedIndex = null,
                isCorrect = false,
                showSuccessAnimation = false,
                showWrongAnimation = false
            )
    }

    fun selectObject(index: Int) {

        if (_state.value.selectedIndex != null) {
            return
        }

        /*
         * The objects are always displayed:
         *
         * index 0 = BIG
         * index 1 = BIGGER
         * index 2 = BIGGEST
         */

        val correctIndex =
            when (_state.value.question) {

                QuestionType.BIG -> 0

                QuestionType.BIGGER -> 1

                QuestionType.BIGGEST -> 2
            }

        val correct =
            index == correctIndex

        _state.value =
            _state.value.copy(
                selectedIndex = index,
                isCorrect = correct,
                score =
                    if (correct)
                        _state.value.score + 1
                    else
                        _state.value.score,
                showSuccessAnimation = correct,
                showWrongAnimation = !correct
            )
    }

    fun nextQuestion() {

        if (_state.value.questionNumber >= 5) {

            _state.value =
                _state.value.copy(
                    gameFinished = true
                )

            return
        }

        _state.value =
            _state.value.copy(
                questionNumber =
                    _state.value.questionNumber + 1
            )

        createQuestion()
    }

    fun restart() {

        _state.value =
            SizeGameState()

        createQuestion()
    }
}