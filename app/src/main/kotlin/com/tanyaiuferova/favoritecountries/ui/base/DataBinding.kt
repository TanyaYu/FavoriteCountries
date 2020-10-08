package com.tanyaiuferova.favoritecountries.ui.base

import android.view.View
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * Author: Tanya Yuferova
 * Date: 10/8/2020
 */
abstract class DataBinding<VH : DataBinding.ViewHolder> {

    var viewHolder: VH? = null

    open fun onViewCreated(view: View) {
        viewHolder = createViewHolder(view)
    }

    open fun onDestroyView() {
        viewHolder = null
    }

    fun <T> binding(
        defaultValue: T,
        action: VH.(item: T) -> Unit
    ) = Delegates.observable(defaultValue, bind(action))

    private fun <T> bind(action: VH.(item: T) -> Unit): (KProperty<*>, T, T) -> Unit {
        return { _, _, newValue ->
            if (viewHolder != null && newValue != null)
                viewHolder!!.apply { action(newValue) }
        }
    }

    abstract fun createViewHolder(view: View): VH

    abstract class ViewHolder(private val view: View)
}
