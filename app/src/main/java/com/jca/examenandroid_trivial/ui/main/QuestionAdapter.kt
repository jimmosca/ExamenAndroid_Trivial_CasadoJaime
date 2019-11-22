package com.jca.examenandroid_trivial.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jca.examenandroid_trivial.R
import com.jca.examenandroid_trivial.ui.model.Question


class QuestionAdapter() : RecyclerView.Adapter<QuestionViewHolder>() {
    var questionList: List<Question> = listOf()

    fun addList(questions: List<Question>) {
        questionList = questions
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder.from(parent)
    }

    override fun getItemCount() = questionList.size

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) =
        holder.bind(questionList[position])
}

class QuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val txtQuestion: TextView = view.findViewById(R.id.questionText)
    private val radioGroup: RadioGroup = view.findViewById(R.id.radioGroup)
    private val firstAnswer: RadioButton = view.findViewById(R.id.firstRadioButton)
    private val secondAnswer: RadioButton = view.findViewById(R.id.secondRadioButton)
    private val thirdAnswer: RadioButton = view.findViewById(R.id.thirdRadioButton)
    private val forthAnswer: RadioButton = view.findViewById(R.id.forthRadioButton)

    fun bind(question: Question) {

        question.answersList = mutableListOf(
            question.correct_answer,
            question.incorrect_answers[0],
            question.incorrect_answers[1],
            question.incorrect_answers[2]
        )

        if (question.answersList.isNotEmpty()) {
            question.answersList.shuffle()
        }


        txtQuestion.text = question.question

        firstAnswer.text = question.answersList[0]
        secondAnswer.text = question.answersList[1]
        thirdAnswer.text = question.answersList[2]
        forthAnswer.text = question.answersList[3]


    }


    companion object {
        fun from(parent: ViewGroup): QuestionViewHolder {
            val movieItem =
                LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
            return QuestionViewHolder(movieItem)
        }
    }
}