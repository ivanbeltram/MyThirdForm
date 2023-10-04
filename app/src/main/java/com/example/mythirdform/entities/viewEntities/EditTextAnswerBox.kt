package com.example.mythirdform.entities.viewEntities

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.children
import com.example.mythirdform.R.string
import com.example.mythirdform.entities.Option
import com.example.mythirdform.entities.NumberInput

class EditTextAnswerBox(
    context: Context,
    attrs: AttributeSet? = null,
    var idNextScreen: Int
) : LinearLayout(context, attrs), AnswerBox {

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)

    override fun isCompleted(): Boolean {
        var isCompleted = true
        var i = 0
        var editTexts = children.toList()
        while (i < editTexts.size && isCompleted) {
            if ((editTexts[i] as EditText).text.isEmpty()) {
                isCompleted = false
            }
            i++
        }
        return isCompleted
    }

    override fun checkCompleted(): Boolean {
        val isCompleted = isCompleted()
        if (!isCompleted) {
            throw Error(context.getString(string.edit_text_answer_box_is_completed_error))
        }
        return isCompleted
    }

    override fun getNextScreenId(): Int {
        checkCompleted()
        return idNextScreen
    }

    override fun getAnswers(): List<Option> {
        checkCompleted()
        return children.toList().map { buildNumberInput(it as EditText) }
    }

    private fun buildNumberInput(option: EditText): NumberInput {
        val id = option.id
        val value = (option.tag as HashMap<*, *>)[context.getString(string.view_tag_value_key)] as Float
        val label = option.hint.toString()
        val measure =
            (option.tag as HashMap<*, *>)[context.getString(string.view_tag_measure_key)] as String
        val inputNumber = option.text.toString().toInt()

        return NumberInput(id, value, label, measure, inputNumber)
    }

}
