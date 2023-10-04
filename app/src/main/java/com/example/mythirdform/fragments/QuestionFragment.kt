package com.example.mythirdform.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import com.example.mythirdform.R
import com.example.mythirdform.databinding.FragmentQuestionBinding
import com.example.mythirdform.entities.AnswerOptions
import com.example.mythirdform.entities.NumberInputs
import com.example.mythirdform.entities.QuestionScreen
import com.example.mythirdform.entities.StringOptions
import com.example.mythirdform.entities.viewEntities.AnswerBox
import com.example.mythirdform.entities.viewEntities.EditTextAnswerBox
import com.example.mythirdform.entities.viewEntities.RadioButtonAnswerBox
import com.google.android.material.snackbar.Snackbar

class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!
    private lateinit var myAnswerBoxContainer: LinearLayout
    private var myRadioButtonAnswerBox: Int = R.layout.answer_box_radio_button
    private var myEditTextAnswerBox: Int = R.layout.answer_box_edit_text
    private var myRadioButtonItem: Int = R.layout.item_radio_button
    private var myEditTextItem: Int = R.layout.item_edit_text

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        myAnswerBoxContainer = binding.myAnswerBoxContainer

        loadQuestionScreen(QuestionFragmentViewModel.getFirstQuestionScreen())

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.buttonPrevious.setOnClickListener {
            onClickButtonPrevious()
        }

        binding.buttonNext.setOnClickListener {
            onClickButtonNext()
        }
    }

    fun onClickButtonPrevious() {
        if (QuestionFragmentViewModel.getQuestionScreenHistorySize() == 1) {
            QuestionFragmentViewModel.exitQuestionFragment(requireActivity().onBackPressedDispatcher)
        } else {
            loadPreviousQuestionScreen()
        }
    }

    fun onClickButtonNext() {
        val myAnswerBox = myAnswerBoxContainer.getChildAt(0) as AnswerBox

        try {
            myAnswerBox.checkCompleted()
            QuestionFragmentViewModel.saveAnswers(myAnswerBox.getAnswers())
            when (QuestionFragmentViewModel.isCurrentQuestionFinal()) {
                true -> {
                    goToResults()
                }

                false -> {
                    goToNextQuestionScreen()
                }
            }
        } catch (e: Error) {
            throwSnackbar(e.message.toString())
        }
    }

    private fun setButtonNextText() {
        when (QuestionFragmentViewModel.isCurrentQuestionFinal()) {
            true -> {
                binding.buttonNext.text = getString(R.string.question_fragment_finish_button)
            }

            false -> {
                binding.buttonNext.text = getString(R.string.question_fragment_next_button)
            }
        }
    }

    private fun loadQuestionScreen(questionScreen: QuestionScreen) {
        QuestionFragmentViewModel.saveQuestion(questionScreen)
        loadQuestion(questionScreen.question)
        loadAnswerBox(questionScreen.answers)
        setButtonNextText()
    }

    private fun loadQuestion(question: String) {
        binding.tvQuestion.text = question
    }

    private fun loadAnswerBox(answers: AnswerOptions) {
        myAnswerBoxContainer.removeAllViews()
        val inflater = LayoutInflater.from(requireContext())

        when (answers) {
            is StringOptions -> loadRadioButtons(answers, inflater)
            is NumberInputs -> loadEditTexts(answers, inflater)
            else -> throw Error()
        }
    }

    private fun loadRadioButtons(options: StringOptions, inflater: LayoutInflater) {
        val myAnswerBox =
            inflater.inflate(
                myRadioButtonAnswerBox,
                myAnswerBoxContainer,
                false
            ) as RadioButtonAnswerBox

        for (option in options.strings) {
            val radioButton =
                inflater.inflate(myRadioButtonItem, myAnswerBox, false) as RadioButton
            radioButton.text = option.label
            radioButton.id = option.id

            val tagValues = HashMap<String, Any>()
            tagValues[getString(R.string.view_tag_value_key)] = option.value
            tagValues[getString(R.string.view_tag_next_screen_id_key)] = option.idNextScreen
            radioButton.tag = tagValues

            myAnswerBox.addView(radioButton)
        }

        this.myAnswerBoxContainer.addView(myAnswerBox)
    }

    private fun loadEditTexts(options: NumberInputs, inflater: LayoutInflater) {
        val myAnswerBox =
            inflater.inflate(myEditTextAnswerBox, myAnswerBoxContainer, false) as EditTextAnswerBox

        myAnswerBox.idNextScreen = options.idNextScreen

        for (option in options.inputs) {
            val editText = inflater.inflate(myEditTextItem, myAnswerBox, false) as EditText
            editText.hint = option.label
            editText.id = option.id

            val tagValues = HashMap<String, Any>()
            tagValues[getString(R.string.view_tag_value_key)] = option.value
            tagValues[getString(R.string.view_tag_measure_key)] = option.measure
            editText.tag = tagValues

            myAnswerBox.addView(editText)
        }

        myAnswerBoxContainer.addView(myAnswerBox)
    }

    private fun loadPreviousQuestionScreen() {
        loadQuestionScreen(QuestionFragmentViewModel.getPreviousQuestionScreen())
    }

    private fun goToNextQuestionScreen() {
        val myAnswerBox = myAnswerBoxContainer.getChildAt(0) as AnswerBox

        try {
            val nextScreenId = myAnswerBox.getNextScreenId()
            loadQuestionScreen(QuestionFragmentViewModel.getNextQuestionScreen(nextScreenId))
        } catch (e: Error) {
            throwSnackbar(e.message.toString())
        }
    }

    private fun goToResults() {
        val action =
            QuestionFragmentDirections.actionQuestionFragmentToResultsFragment()
        findNavController().navigate(action)
    }

    private fun throwSnackbar(text: String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_SHORT).show()
    }
}
