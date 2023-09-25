package com.example.mythirdform.services

import com.example.mythirdform.entities.Category
import com.example.mythirdform.entities.viewEntities.EditTextAnswerBox
import com.example.mythirdform.entities.NumberInput
import com.example.mythirdform.entities.NumberInputs
import com.example.mythirdform.entities.QuestionScreen
import com.example.mythirdform.entities.StringOption
import com.example.mythirdform.entities.StringOptions
import com.example.mythirdform.entities.viewEntities.RadioButtonAnswerBox

class FormRepository {

    var oneForm: MutableList<QuestionScreen> = mutableListOf()

    init {
        oneForm.add(
            QuestionScreen(
                1, false, Category.TRANSPORT, "Pregunta 1", StringOptions(
                    mutableListOf(
                        StringOption(1, 1.0f, "Opción 2", 2),
                        StringOption(2, 1.0f, "Opción 3", 3),
                        StringOption(3, 1.0f, "Opción 4", 4)
                    )
                )
            )
        )
        oneForm.add(
            QuestionScreen(
                2, false, Category.ENERGY, "Pregunta 2", StringOptions(
                    mutableListOf(
                        StringOption(5, 1.0f, "Opción 3", 3),
                        StringOption(6, 1.0f, "Opción 4", 4)
                    )
                )
            )
        )
        oneForm.add(
            QuestionScreen(
                3, false, Category.WATER, "Pregunta 3", NumberInputs(
                    mutableListOf(
                        NumberInput(8, 1.0f, "Combustible", "L"),
                        NumberInput(9, 1.0f, "Dióxido de carbono", "cmª"),
                    ), 4
                )
            )
        )
        oneForm.add(
            QuestionScreen(
                4, true, Category.WASTE, "Pregunta 4", StringOptions(
                    mutableListOf(
                        StringOption(10, 1.0f, "Opción 5", 9),
                        StringOption(11, 1.0f, "Opción 6", 9),
                        StringOption(12, 1.0f, "Opción 7", 9),
                        StringOption(13, 1.0f, "Opción 8", 9),
                    )
                )
            )
        )
    }
}
