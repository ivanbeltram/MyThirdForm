package com.example.mythirdform.entities

class StringOptions(var strings: MutableList<StringOption>) : AnswerOptions() {

    override fun getPossibleAnswersCount(): Int {
        return 1
    }

}
