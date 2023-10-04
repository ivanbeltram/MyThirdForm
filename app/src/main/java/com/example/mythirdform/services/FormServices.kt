package com.example.mythirdform.services

import com.example.mythirdform.entities.Option
import com.example.mythirdform.entities.QuestionScreen

object FormServices {

    fun getFirstQuestionScreen(): QuestionScreen {
        return mySimulationGetFirstQuestionScreen()
    }

    fun getNextQuestionScreen(answer: Int): QuestionScreen {
        return mySimulationGetNextQuestionScreen(answer)
    }

    fun getResults(userAnswers: MutableList<Option>): Float {
        return mySimulationGetResults(userAnswers)
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //


    private var questionScreenList = FormRepository().oneForm

    private fun mySimulationGetFirstQuestionScreen(): QuestionScreen {
        return findQuestionScreen(1)
    }

    private fun findQuestionScreen(id: Int): QuestionScreen {
        if(questionScreenList.find { it.id == id } == null){
            throw Error("No se encontró la siguiente pregunta.")
        }
        return questionScreenList.find { it.id == id }!!
    }

    private fun mySimulationGetNextQuestionScreen(answer: Int): QuestionScreen {
        return findQuestionScreen(answer)
    }

    private fun mySimulationGetResults(userAnswers: MutableList<Option>): Float {
        var valuesSum: Float = 0f
        userAnswers.forEach { valuesSum += it.value }
        return valuesSum
    }

}
