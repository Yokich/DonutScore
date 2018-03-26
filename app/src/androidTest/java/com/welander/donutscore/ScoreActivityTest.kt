package com.welander.donutscore


import android.support.test.runner.AndroidJUnit4
import com.welander.donutscore.models.CreditScoreContainer
import com.welander.donutscore.models.CreditScoreInformation
import com.welander.donutscore.network.CreditScoreService
import com.welander.donutscore.network.CreditScoreStore
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

/**
 * Crafted by welander on 2018-03-25.
 */
@RunWith(AndroidJUnit4::class)
class ScoreActivityTest {

    @Mock
    lateinit var creditScoreService: CreditScoreService

    lateinit var creditScoreStore: CreditScoreStore

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        creditScoreStore = CreditScoreStore(service = creditScoreService)
    }

//    val observer: TestObserver


    @Test
    fun testSuccess() {
        mockCreditInformation(550, 0, 850)
        // todo
    }

    @Test
    fun testLoading() {

    }

    @Test
    fun testError() {

    }

    @Test
    fun testZeroMinValue() {

    }

    private fun mockLoading() {
        Mockito.`when`(creditScoreService.getCreditScore()).thenReturn(
                Single.just(CreditScoreContainer(
                        information = CreditScoreInformation(
                                score = 0,
                                minValue = 0,
                                maxValue = 0
                        )
                )).delay(50, TimeUnit.HOURS)
        )
    }

    private fun mockError() {
        Mockito.`when`(creditScoreService.getCreditScore()).thenReturn(
                Single.just(CreditScoreContainer(
                        information = CreditScoreInformation(
                                score = 0,
                                minValue = 0,
                                maxValue = 0
                        )
                )).doOnSuccess { throw UnknownHostException() }
        )
    }

    private fun mockCreditInformation(score: Int, minValue: Int, maxValue: Int) {
        Mockito.`when`(creditScoreService.getCreditScore()).thenReturn(
                Single.just(CreditScoreContainer(
                        information = CreditScoreInformation(
                                score = score,
                                minValue = minValue,
                                maxValue = maxValue
                        )
                ))
        )
    }
}

