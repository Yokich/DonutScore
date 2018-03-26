package com.welander.donutscore.models

import com.google.gson.annotations.SerializedName

/**
 * Crafted by welander on 2018-03-17.
 * For a larger application, consider separating DTO's and business logic models
 */
data class CreditScoreInformation(
        @SerializedName("score")
        val score: Int,
        @SerializedName("maxScoreValue")
        val maxValue: Int,
        @SerializedName("minScoreValue")
        val minValue: Int
)
// No other of the available values seems necessary