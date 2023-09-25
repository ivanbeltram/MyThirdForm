package com.example.mythirdform.entities

class NumberInputs(var inputs: MutableList<NumberInput>, var idNextScreen: Int) : AnswerOptions() {

    override fun getPossibleAnswersCount(): Int {
        return inputs.size
    }

}
