package com.welander.donutscore.application

import com.welander.donutscore.activities.ScoreActivity
import com.welander.donutscore.network.ApiModule
import dagger.Component

/**
* Crafted by welander on 2018-03-17.
*/
@DonutScoreScope
@Component(modules = [ApiModule::class])
interface DonutScoreApplicationComponent {
/** Public Interface to application */

    fun scoreActivity(scoreActivity: ScoreActivity)

}