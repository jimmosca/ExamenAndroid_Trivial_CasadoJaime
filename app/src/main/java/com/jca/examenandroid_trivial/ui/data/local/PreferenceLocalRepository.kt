package com.jca.examenandroid_trivial.ui.data.local

import android.content.SharedPreferences

class PreferenceLocalRepository(private val preferences: SharedPreferences): LocalRepository {

    private val scoreKey = "Score Key"
    override suspend fun setLastScore(score: Int) {
        val editor = preferences.edit()
        editor.putInt(scoreKey, score)

        editor.apply()
    }

    override suspend fun getLastScore(): Int {
        return preferences.getInt(scoreKey, 0)
    }
}