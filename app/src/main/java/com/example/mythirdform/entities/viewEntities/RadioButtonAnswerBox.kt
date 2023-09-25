package com.example.mythirdform.entities.viewEntities

import android.content.Context
import android.util.AttributeSet
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.children
import com.example.mythirdform.R.string
import com.example.mythirdform.entities.Option
import com.example.mythirdform.entities.StringOption

class RadioButtonAnswerBox(
    context: Context,
    attrs: AttributeSet? = null
) : RadioGroup(context, attrs), AnswerBox {

    override fun isCompleted(): Boolean {
        var isCompleted = true
        val checkedRadioButtonId = checkedRadioButtonId
        if (checkedRadioButtonId == -1) {
            isCompleted = false
        }
        return isCompleted
    }

    override fun checkCompleted(): Boolean {
        val isCompleted = isCompleted()
        if (!isCompleted) {
            throw Error(context.getString(string.radio_button_answer_box_is_completed_error))
        }
        return isCompleted
    }

    override fun getNextScreenId(): Int {
        checkCompleted()
        val checkedRadioButtonId = checkedRadioButtonId
        val checkedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
        return (checkedRadioButton.tag as HashMap<*, *>)[context.getString(string.view_tag_next_screen_id_key)] as Int
    }

    override fun getAnswers(): List<Option> {
        checkCompleted()
        val checkedRadioButtonId = checkedRadioButtonId
        val checkedRadioButton = findViewById<RadioButton>(checkedRadioButtonId)
        return listOf<Option>(buildStringOption(checkedRadioButton))
    }

    private fun buildStringOption(option: RadioButton): StringOption {
        val id = option.id
        val value =
            (option.tag as HashMap<*, *>)[context.getString(string.view_tag_value_key)] as Float
        val label = option.text.toString()
        val idNextScreen =
            (option.tag as HashMap<*, *>)[context.getString(string.view_tag_next_screen_id_key)] as Int
        return StringOption(id, value, label, idNextScreen)
    }

}
