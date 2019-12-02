package com.jca.examenandroid_trivial.ui.data.local

interface LocalRepository {
    suspend fun getLastScore(): Int
    suspend fun setLastScore(score: Int)
}