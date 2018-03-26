package com.welander.donutscore

import com.welander.donutscore.network.CreditScoreService
import com.welander.donutscore.network.CreditScoreStore
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


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

}