package com.jca.examenandroid_trivial.ui.data.remote

import com.jca.examenandroid_trivial.ui.model.Question

interface RemoteRepository {
    suspend fun getQuestions(): List<Question>?
}