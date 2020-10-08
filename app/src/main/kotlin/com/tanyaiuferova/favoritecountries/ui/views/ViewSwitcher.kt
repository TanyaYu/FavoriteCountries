package com.tanyaiuferova.favoritecountries.ui.views

import android.view.View
import androidx.annotation.IdRes
import androidx.core.view.isGone
import androidx.core.view.isVisible

/**
 * Author: Tanya Yuferova
 * Date: 10/8/2020
 */
class ViewSwitcher(
    vararg val views: View
) {

    init {
        if (views.isNotEmpty())
            displayAt(0)
    }

    fun displayAt(index: Int) {
        views.forEachIndexed { i, view ->
            if (i != index)
                view.isGone = true
            else view.isVisible = true
        }
    }

    fun display(view: View) {
        views.forEach {
            if (it !== view)
                it.isGone = true
            else it.isVisible = true
        }
    }
}