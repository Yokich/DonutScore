package com.welander.donutscore.models

import com.google.gson.annotations.SerializedName

/**
* Crafted by welander on 2018-03-17.
*/
data class CreditScoreContainer(
        @SerializedName("creditReportInfo")
        val information: CreditScoreInformation
)
// No other of the available values seems necessary