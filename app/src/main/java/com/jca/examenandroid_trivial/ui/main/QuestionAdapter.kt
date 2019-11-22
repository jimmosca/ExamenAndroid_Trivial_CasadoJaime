package com.jca.examenandroid_trivial.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jca.examenandroid_trivial.R
import com.jca.examenandroid_trivial.ui.model.Question


class QuestionAdapter(private val presenter: MainPresenter) :
    RecyclerView.Adapter<QuestionViewHolder>() {
    var questionList: List<Question> = listOf()

    fun addList(questions: List<Question>) {
        questionList = questions
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder.from(parent, presenter)
    }

    override fun getItemCount() = questionList.size

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) =
        holder.bind(questionList[position])
}

class QuestionViewHolder(val view: View, val presenter: MainPresenter) :
    RecyclerView.ViewHolder(view),
    HolderView {
    override fun resetBackGround() {
        val selectedButton = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
        selectedButton.setBackgroundColor(Color.WHITE)
        radioGroup.clearCheck()
    }

    override fun verifyResponse(): Int {
        val selectedButton = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
        if (selectedButton != null) {
            return if (selectedButton.text.equals(correctAnswer)) {
                selectedButton.setBackgroundColor(Color.GREEN)
                1
            } else {
                selectedButton.setBackgroundColor(Color.RED)
                0
            }
        } else
            return 0


    }

    private val txtQuestion: TextView = view.findViewById(R.id.questionText)
    private val radioGroup: RadioGroup = view.findViewById(R.id.radioGroup)
    private val firstAnswer: RadioButton = view.findViewById(R.id.firstRadioButton)
    private val secondAnswer: RadioButton = view.findViewById(R.id.secondRadioButton)
    private val thirdAnswer: RadioButton = view.findViewById(R.id.thirdRadioButton)
    private val forthAnswer: RadioButton = view.findViewById(R.id.forthRadioButton)

    lateinit var correctAnswer: String

    fun bind(question: Question) {

        question.answersList = mutableListOf(
            question.correct_answer,
            question.incorrect_answers[0],
            question.incorrect_answers[1],
            question.incorrect_answers[2]
        )
        correctAnswer = question.correct_answer
        if (question.answersList.isNotEmpty()) {
            question.answersList.shuffle()
        }


        txtQuestion.text = question.question

        firstAnswer.text = question.answersList[0]
        secondAnswer.text = question.answersList[1]
        thirdAnswer.text = question.answersList[2]
        forthAnswer.text = question.answersList[3]

        presenter.addViewHolder(this)

    }


    companion object {
        fun from(parent: ViewGroup, presenter: MainPresenter): QuestionViewHolder {
            val movieItem =
                LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
            return QuestionViewHolder(movieItem, presenter)
        }
    }
}