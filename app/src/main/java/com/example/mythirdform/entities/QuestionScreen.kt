package com.example.mythirdform.entities

class QuestionScreen(
    var id: Int,
    var isFinal: Boolean,
    var category: Category,
    var question: String,
    var answers: AnswerOptions
) {
}
