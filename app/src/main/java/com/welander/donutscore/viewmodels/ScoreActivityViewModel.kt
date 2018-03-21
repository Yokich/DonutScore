package com.welander.donutscore.viewmodels

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.welander.donutscore.R
import com.welander.donutscore.models.CreditScoreInformation
import com.welander.donutscore.network.CreditScoreStore
import com.welander.donutscore.utils.safePercent
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber

/**
 * Created by welander on 2018-03-17.
 */

class ScoreActivityViewModel constructor(private val creditScoreStore: CreditScoreStore) : BaseObservable() {

    @Bindable
    var percentageValue: Int = 1 // Make sure minimum value for Pie is not 0, it disappears
        get() = Math.max(1, creditScoreInformation.score safePercent creditScoreInformation.maxValue)

    val maxValueTemplateString = R.string.label_pie_max_value

    @Bindable
    var maxValue: String = ""
        get() = creditScoreInformation.maxValue.toString()

    @Bindable
    var scoreLabel = ""
        get() = creditScoreInformation.score.toString()

    private var creditScoreInformation: CreditScoreInformation = CreditScoreInformation(0, 0, 0)

    private fun showError(e: Throwable) {
        // TODO
        Timber.e("TODO - implement error handling")
        Timber.e("Error: ${e.localizedMessage}")
    }

    private fun showLoading(show: Boolean) {
        // TODO
        Timber.w("TODO - Implement loading...")

    }

    private fun showResult(info: CreditScoreInformation) {
        Timber.i("show result: $info")
        creditScoreInformation = info
        notifyChange()
    }

    fun fetchCreditScore() {
        creditScoreStore.fetchCreditScoreInformation().subscribeWith(object : SingleObserver<CreditScoreInformation> {
            override fun onSuccess(successValue: CreditScoreInformation) {
                showLoading(false)
                showResult(successValue)
            }

            override fun onSubscribe(d: Disposable) {
                Timber.i("Subscribed for credit score")
                showLoading(true)
            }

            override fun onError(e: Throwable) {
                showLoading(false)
                showError(e)
            }

        })
    }

}