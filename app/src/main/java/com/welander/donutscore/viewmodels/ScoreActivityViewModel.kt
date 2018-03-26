package com.welander.donutscore.viewmodels

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.welander.donutscore.BR
import com.welander.donutscore.models.CreditScoreInformation
import com.welander.donutscore.network.CreditScoreStore
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber

/**
 * Crafted by welander on 2018-03-17.
 */

class ScoreActivityViewModel constructor(private val creditScoreStore: CreditScoreStore) : BaseObservable() {

    val errorViewModel = ErrorViewModel(this::fetchCreditScore)
    val donutViewModel = DonutViewModel()

    @Bindable
    var showLoading = false

    private fun showLoading(show: Boolean) {
        showLoading = show
        notifyPropertyChanged(BR.showLoading)
    }

    private fun showResult(info: CreditScoreInformation) {
        donutViewModel.visible = true
        donutViewModel.updateData(info)
    }

    fun fetchCreditScore() {
        creditScoreStore.fetchCreditScoreInformation().subscribeWith(creditScoreObserver)
    }

    private val creditScoreObserver = object : SingleObserver<CreditScoreInformation> {
        override fun onSuccess(successValue: CreditScoreInformation) {
            Timber.i("Got Credit Score $successValue")
            showLoading(false)
            showResult(successValue)
        }

        override fun onSubscribe(d: Disposable) {
            Timber.i("Subscribed for credit score")
            donutViewModel.visible = false
            errorViewModel.visible = false
            showLoading(true)
        }

        /**
         * For this simple application we just handle the errors like this
         * For a more advanced application we might introduce states in the
         * reactive stream, and modify the values returned on error
         */
        override fun onError(e: Throwable) {
            Timber.e("Error: ${e.localizedMessage}")
            showLoading(false)
            errorViewModel.visible = true
        }
    }

}