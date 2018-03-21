package com.welander.donutscore.utils

import android.databinding.BindingAdapter
import android.support.annotation.StringRes
import android.widget.TextView

/**
 * Crafted by welander on 2018-03-21.
 */
//
@BindingAdapter(value = ["template", "insertText"], requireAll = true)
fun insertTextIntoTemplate(view: TextView, @StringRes template: Int, insertText: String) {
    view.text = view.resources.getString(template, insertText)
}

@BindingAdapter(value = ["template", "insertText"], requireAll = true)
fun insertText(view: TextView, template: String, insertText: String) {
    view.text = String.format(template, insertText)
}