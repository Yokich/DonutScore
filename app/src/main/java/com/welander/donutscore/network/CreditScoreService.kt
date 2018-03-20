package com.welander.donutscore.network

import com.welander.donutscore.models.CreditScoreContainer
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by welander on 2018-03-17.
 */
interface CreditScoreService {

    @GET("prod/mockcredit/values")
    fun getCreditScore(): Single<CreditScoreContainer>

}