package com.jca.examenandroid_trivial.ui.main


import android.util.Log
import com.jca.examenandroid_trivial.ui.data.local.LocalRepository
import com.jca.examenandroid_trivial.ui.data.remote.RemoteRepository
import com.jca.examenandroid_trivial.ui.model.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPresenter(
    private val view: MainView,
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) {

    fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            val remoteData = remoteRepository.getQuestions()
            val localData = localRepository.getLastScore()
            withContext(Dispatchers.Main) {
                if (remoteData != null) {
                    view.showQuestions(remoteData)
                } else
                    view.showError()

                view.showScore(localData)
            }
        }
    }

    fun onSendClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onNewGameClicked() {
        CoroutineScope(Dispatchers.IO).launch {
            val remoteData = remoteRepository.getQuestions()

            withContext(Dispatchers.Main){
                if (remoteData != null) {
                    view.showQuestions(remoteData)
                } else
                    view.showError()
            }
        }
    }

}

interface MainView {
    fun showQuestions(remoteData: List<Question>)
    fun showError()
    fun showScore(score: Int)

}