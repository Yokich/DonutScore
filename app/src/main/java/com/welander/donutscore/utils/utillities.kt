package com.welander.donutscore.utils

/**
 * Crafted by welander on 2018-03-21.
 */

infix fun Int.safePercent(other: Int): Int =
        (if (this == 0 || other == 0) 0f
        else this.toFloat() / other.toFloat()).let { (it * 100).toInt() }
