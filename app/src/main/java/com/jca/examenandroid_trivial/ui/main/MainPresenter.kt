package com.jca.examenandroid_trivial.ui.main


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

    private val viewHolders: MutableList<HolderView> = mutableListOf()

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
        var count: Int = 0
        for (item in viewHolders){
            count += item.verifyResponse()
        }
        CoroutineScope(Dispatchers.IO).launch {
            localRepository.setLastScore(count)

            withContext(Dispatchers.Main){
                view.showScore(count)
            }
        }



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
        for (item in viewHolders){
            item.resetBackGround()
        }
    }
    fun addViewHolder(viewHolder: HolderView){
        viewHolders.add(viewHolder)
    }

}

interface MainView {
    fun showQuestions(remoteData: List<Question>)
    fun showError()
    fun showScore(score: Int)
}

interface HolderView{
    fun verifyResponse(): Int
    fun resetBackGround()

}