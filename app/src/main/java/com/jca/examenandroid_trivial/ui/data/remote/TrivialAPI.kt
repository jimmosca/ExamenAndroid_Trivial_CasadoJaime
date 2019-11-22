package com.jca.examenandroid_trivial.ui.data.remote

import com.jca.examenandroid_trivial.ui.model.QuestionsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TrivialAPI {
    @GET("api.php")
    suspend fun getQuestions(@Query("amount") amount: Int = 5, @Query("type") type: String = "multiple"): Response<QuestionsResponse>
}

object RetrofitFactory {
    private const val BASE_URL = "https://opentdb.com/"

    fun makeRetrofitService(): TrivialAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(TrivialAPI::class.java)
    }
}