package com.welander.donutscore.viewmodels

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.welander.donutscore.models.CreditScoreInformation
import com.welander.donutscore.network.CreditScoreStore
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber

/**
 * Created by welander on 2018-03-17.
 */

class ScoreActivityViewModel constructor(private val creditScoreStore: CreditScoreStore) : BaseObservable() {

    @Bindable
    var minValue: Int = 0
        get() = creditScoreInformation.minValue

    @Bindable
    var maxValue: Int = 0
        get() = creditScoreInformation.maxValue

    @Bindable
    var score: Int = 0
        get() = creditScoreInformation.score

    var creditScoreInformation: CreditScoreInformation = CreditScoreInformation(0, 0, 0)

    fun fetchCreditScore() {
        creditScoreStore.fetchCreditScoreInformation().subscribeWith(object : SingleObserver<CreditScoreInformation> {
            override fun onSuccess(successValue: CreditScoreInformation) {
                Timber.i("success $successValue")
                // Stop loading
                creditScoreInformation = successValue
                notifyChange()

            }

            override fun onSubscribe(d: Disposable) {
                // Start loading
            }

            override fun onError(e: Throwable) {
                Timber.e("Error: ${e.localizedMessage}")
                // Stop loading
                // Show error screen
            }

        })
    }

}