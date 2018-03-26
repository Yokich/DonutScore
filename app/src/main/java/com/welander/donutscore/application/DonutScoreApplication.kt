package com.welander.donutscore.application

import android.app.Application
import android.content.Context
import timber.log.Timber
import timber.log.Timber.DebugTree


/**
* Crafted by welander on 2018-03-17.
*/
class DonutScoreApplication : Application() {

    lateinit var creditScoreComponent: DonutScoreApplicationComponent

    override fun onCreate() {
        super.onCreate()

        Timber.plant(DebugTree())

        creditScoreComponent = DaggerDonutScoreApplicationComponent.builder()
                .build()
    }

    companion object {
        fun creditScoreComponent(context: Context): DonutScoreApplicationComponent {
            return (context.applicationContext as DonutScoreApplication).creditScoreComponent
        }
    }

}