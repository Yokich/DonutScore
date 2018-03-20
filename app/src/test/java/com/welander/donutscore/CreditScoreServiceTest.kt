package com.welander.donutscore

import com.welander.donutscore.models.CreditScoreContainer
import com.welander.donutscore.models.CreditScoreInformation
import com.welander.donutscore.network.CreditScoreService
import com.welander.donutscore.network.CreditScoreStore
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import timber.log.Timber


/**
 * Created by welander on 2018-03-17.
 */
@RunWith(MockitoJUnitRunner::class)
class CreditScoreServiceTest {
    @Mock
    lateinit var creditScoreService: CreditScoreService

    lateinit var creditScoreStore: CreditScoreStore

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        creditScoreStore = CreditScoreStore(service = creditScoreService)
    }

    @Test
    fun testSomething() {

        val minValue = 0
        `when`(creditScoreService.getCreditScore()).thenReturn(
                Single.just(CreditScoreContainer(
                        information = CreditScoreInformation(
                                score = 555,
                                minValue = minValue,
                                maxValue = 850
                        )
                ))
        )


        creditScoreStore.fetchCreditScoreInformation().subscribe { info ->
            Timber.i("info: $info")
        }

    }
    // todo, actual tests.
    // https://stackoverflow.com/questions/39827081/unit-testing-android-application-with-retrofit-and-rxjava
}