package com.jca.examenandroid_trivial.ui.model




data class QuestionsResponse(val response_code: Int, val results: List<Question>)

data class Question(
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>,
    var answersList: MutableList<String>
)