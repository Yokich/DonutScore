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
        setupDagger()
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    private fun setupDagger() {
        DonutScoreApplication
                .creditScoreComponent(this)
                .scoreActivity(this)
    }

    private fun setupViewModel() {
        viewModel = ScoreActivityViewModel(store)

        val binding = DataBindingUtil.setContentView<ActivityScoreBinding>(this, R.layout.activity_score)
        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchCreditScore()
    }
}
