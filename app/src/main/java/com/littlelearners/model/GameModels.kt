package com.littlelearners.model

enum class SizeLevel(
    val label: String,
    val sizeDp: Int
) {
    BIG("BIG", 100),
    BIGGER("BIGGER", 150),
    BIGGEST("BIGGEST", 200)
}

data class GameObject(
    val name: String,
    val emoji: String
)

enum class QuestionType {
    BIG,
    BIGGER,
    BIGGEST
}

data class Question(
    val type: QuestionType,
    val targetIndex: Int
)