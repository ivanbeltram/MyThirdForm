package com.example.mythirdform.fragments

import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.ViewModel
import com.example.mythirdform.entities.Option
import com.example.mythirdform.entities.QuestionScreen
import com.example.mythirdform.services.FormServices

object QuestionFragmentViewModel : ViewModel() {

    private var questionScreenHistory = mutableListOf<QuestionScreen>()
    private var userAnswers = mutableListOf<Option>()
    private var isCurrentQuestionFinal = false

    fun isCurrentQuestionFinal(): Boolean {
        return isCurrentQuestionFinal
    }

    fun getQuestionScreenHistorySize(): Int {
        return questionScreenHistory.size
    }

    fun saveQuestion(questionScreen: QuestionScreen) {
        questionScreenHistory.add(questionScreen)
    }

    fun saveAnswers(answers: List<Option>) {
        userAnswers.addAll(answers)
    }

    fun exitQuestionFragment(onBackPressedDispatcher: OnBackPressedDispatcher) {
        questionScreenHistory.clear()
        userAnswers.clear()
        onBackPressedDispatcher.onBackPressed()
    }

    fun getFirstQuestionScreen(): QuestionScreen {
        var firstQuestionScreen = FormServices.getFirstQuestionScreen()
        isCurrentQuestionFinal = firstQuestionScreen.isFinal
        return firstQuestionScreen
    }

    fun getPreviousQuestionScreen(): QuestionScreen {
        isCurrentQuestionFinal = false
        questionScreenHistory.removeLast()
        val questionScreenToLoad = questionScreenHistory.removeLast()
        userAnswers.removeAt(userAnswers.size - questionScreenToLoad.answers.getPossibleAnswersCount())
        return questionScreenToLoad
    }

    fun getNextQuestionScreen(idNextScreen: Int): QuestionScreen {
        var nextQuestionScreen = FormServices.getNextQuestionScreen(idNextScreen)
        isCurrentQuestionFinal = nextQuestionScreen.isFinal
        return nextQuestionScreen
    }

    fun getResults(): Float {
        var myResults = FormServices.getResults(userAnswers)
        questionScreenHistory.clear()
        userAnswers.clear()
        return myResults
    }

}
