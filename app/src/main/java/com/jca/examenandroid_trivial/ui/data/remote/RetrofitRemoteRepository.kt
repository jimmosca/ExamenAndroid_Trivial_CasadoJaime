package com.jca.examenandroid_trivial.ui.data.remote

import com.jca.examenandroid_trivial.ui.model.Question

class RetrofitRemoteRepository(private val trivialAPI: TrivialAPI): RemoteRepository {
    override suspend fun getQuestions(): List<Question>? {
        val response = trivialAPI.getQuestions()
        return if (response.isSuccessful && response.body()?.response_code==0){
            val questionList = response.body()!!.results
            questionList
        }else
            null

    }
}