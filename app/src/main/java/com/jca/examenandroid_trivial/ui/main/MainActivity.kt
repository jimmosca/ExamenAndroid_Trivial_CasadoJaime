package com.jca.examenandroid_trivial.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.jca.examenandroid_trivial.R
import com.jca.examenandroid_trivial.ui.data.local.PreferenceLocalRepository
import com.jca.examenandroid_trivial.ui.data.remote.RetrofitFactory
import com.jca.examenandroid_trivial.ui.data.remote.RetrofitRemoteRepository
import com.jca.examenandroid_trivial.ui.model.Question
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {


    lateinit var adapter: QuestionAdapter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = QuestionAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        presenter = MainPresenter(
            this, PreferenceLocalRepository(
                getSharedPreferences(
                    "score_preference",
                    Context.MODE_PRIVATE
                )
            ), RetrofitRemoteRepository(RetrofitFactory.makeRetrofitService())
        )

        presenter.init()
    }

    override fun showQuestions(remoteData: List<Question>) {
        adapter.addList(remoteData)
    }

    override fun showError() {
        Toast.makeText(this, "Something went wrong, try a new Game", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.appbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.sendMenuOption -> presenter.onSendClicked()
            R.id.newGameMenuOption -> presenter.onNewGameClicked()


        }
        return true
    }

    override fun showScore(score: Int) {
        txtLastScore.text = txtLastScore.text.toString() + score
    }
}
