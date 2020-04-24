package com.yksong.simpletype

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.RuntimeException

internal class TypeHelper {
    private val binderMap = mutableMapOf<Int, ItemViewBinder<*, *>>()
    private val typeMap = mutableMapOf<Class<*>, ItemViewBinder<*, *>>()

    fun <T> register(clazz: Class<T>, binder: ItemViewBinder<T, *>) {
        binderMap[binder.layoutId] = binder
        typeMap[clazz] = binder
    }

    fun <T> geiItemViewType(clazz: Class<T>) : Int {
        return typeMap[clazz]?.layoutId ?: throw RuntimeException("$clazz is not supported.")
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        return binderMap[viewType]?.onCreateViewHolder(parent)
            ?: throw RuntimeException("ViewBinder cannot be found.")
    }

    @Suppress("UNCHECKED_CAST")
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, payload: Any) {
        val binder = typeMap[payload.javaClass] as ItemViewBinder<Any, RecyclerView.ViewHolder>
        binder.onBindViewHolder(holder, payload)
    }
}