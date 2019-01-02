package com.kroegerama.kaiteki.recyclerview.adapter

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in T>(view: View, protected val context: Context) : RecyclerView.ViewHolder(view) {

    protected val resources: Resources
        get() = context.resources

    open val clickableViews: Array<View>? = null

    abstract fun update(item: T, position: Int)
}
