package com.yksong.simpletype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

abstract class ItemViewBinder<T, VH: RecyclerView.ViewHolder> {
    @get:LayoutRes
    abstract val layoutId : Int

    fun onCreateViewHolder(parent: ViewGroup) : VH {
        return onCreateViewHolder(LayoutInflater.from(parent.context)
            .inflate(layoutId, parent, false))
    }

    protected abstract fun onCreateViewHolder(view: View) : VH

    abstract fun onBindViewHolder(holder: VH, item: T)
}
