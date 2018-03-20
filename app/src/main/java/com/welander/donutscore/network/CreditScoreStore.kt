package com.welander.donutscore.network

import com.welander.donutscore.application.DonutScoreScope
import com.welander.donutscore.models.CreditScoreInformation
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by welander on 2018-03-17.
 */
@DonutScoreScope
class CreditScoreStore @Inject constructor (private val service: CreditScoreService) {

     fun fetchCreditScoreInformation(): Single<CreditScoreInformation> {
        return service.getCreditScore()
                .subscribeOn(Schedulers.io())
                .map { it.information }
    }
}