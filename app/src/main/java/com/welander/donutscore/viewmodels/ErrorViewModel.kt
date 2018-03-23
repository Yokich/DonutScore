package com.welander.donutscore.viewmodels

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.welander.donutscore.BR
import kotlin.reflect.KFunction0 as function

/**
 * Crafted by welander on 2018-03-23.
 */
class ErrorViewModel(private val retry: function<Unit>) : BaseObservable() {

    @Bindable
    var visible: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.visible)
        }

    fun retryClick() {
        retry()
    }


}