package com.welander.donutscore.viewmodels

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.welander.donutscore.BR
import com.welander.donutscore.models.CreditScoreInformation
import com.welander.donutscore.network.CreditScoreStore
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created by welander on 2018-03-17.
 */

class ScoreActivityViewModel constructor(private val creditScoreStore: CreditScoreStore) : BaseObservable() {

    val errorViewModel = ErrorViewModel(this::fetchCreditScore)
    val donutViewModel = DonutViewModel()

    @Bindable
    var showLoading = false

    fun fetchCreditScore() {
        // creditScoreStore.fetchCreditScoreInformation().subscribeWith(creditScoreObserver)
//        mockScore()
//        mockLoading()
        mockError()
    }

    val creditScoreObserver = object : SingleObserver<CreditScoreInformation> {
        override fun onSuccess(successValue: CreditScoreInformation) {
            showLoading(false)
            showResult(successValue)
        }

        override fun onSubscribe(d: Disposable) {
            Timber.i("Subscribed for credit score")
            donutViewModel.visible = false
            errorViewModel.visible = false
            showLoading(true)
        }

        override fun onError(e: Throwable) {
            showLoading(false)
            showError(e)
        }
    }

    private fun showLoading(show: Boolean) {
        showLoading = show
        notifyPropertyChanged(BR.showLoading)
    }

    private fun showResult(info: CreditScoreInformation) {
        donutViewModel.visible = true
        donutViewModel.updateData(info)
    }

    private fun showError(e: Throwable) {
        Timber.e("Error: ${e.localizedMessage}")
        errorViewModel.visible = true
    }

    // Development methods

    fun mockScore() {
        Single.just(CreditScoreInformation(300, 700, 0))
                .subscribeWith(creditScoreObserver)
    }

    fun mockLoading() {
        Single.just(CreditScoreInformation(300, 700, 0))
                .delay(5, TimeUnit.SECONDS)
                .subscribeWith(creditScoreObserver)
    }

    fun mockError() {
        val errorSingle: Single<CreditScoreInformation> = Single.error(Throwable("BIG ERROR"))
        // todo, add subscribeOn
        // todo, add Observe on
        // todo, handle exceptions
        // todo, tests for these
        errorSingle
                .delay(500, TimeUnit.MILLISECONDS)
                .subscribeWith(creditScoreObserver)
    }


}