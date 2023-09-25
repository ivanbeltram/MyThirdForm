package com.example.mythirdform.entities.viewEntities

import com.example.mythirdform.entities.Option

interface AnswerBox {
    fun isCompleted(): Boolean
    fun checkCompleted(): Boolean
    fun getNextScreenId(): Int
    fun getAnswers(): List<Option>
}
