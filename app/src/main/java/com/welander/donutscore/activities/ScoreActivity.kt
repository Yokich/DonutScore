package com.welander.donutscore.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.welander.donutscore.R
import com.welander.donutscore.application.DonutScoreApplication
import com.welander.donutscore.databinding.ActivityScoreBinding
import com.welander.donutscore.network.CreditScoreStore
import com.welander.donutscore.viewmodels.ScoreActivityViewModel
import javax.inject.Inject

class ScoreActivity : AppCompatActivity() {

    @Inject
    lateinit var store: CreditScoreStore

    lateinit var viewModel: ScoreActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        DonutScoreApplication
                .creditScoreComponent(this)
                .scoreActivity(this)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityScoreBinding>(this, R.layout.activity_score)

        viewModel = ScoreActivityViewModel(store)
        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchCreditScore()
    }
}
