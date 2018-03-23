package com.welander.donutscore.viewmodels

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.welander.donutscore.BR
import com.welander.donutscore.R
import com.welander.donutscore.models.CreditScoreInformation
import com.welander.donutscore.utils.safePercent

/**
 * Crafted by welander on 2018-03-23.
 */
class DonutViewModel : BaseObservable() {

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

    @Bindable
    var visible = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.visible)
        }

    var creditScoreInformation: CreditScoreInformation = CreditScoreInformation(0, 0, 0)

    fun updateData(info: CreditScoreInformation) {
        creditScoreInformation = info
        notifyChange()
    }

}