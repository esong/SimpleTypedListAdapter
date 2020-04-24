package com.yksong.simpletype

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class SimpleTypedListAdapter :
    ListAdapter<Any, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem.javaClass == newItem.javaClass
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem == newItem
        }
    }) {

    private val helper = TypeHelper()

    inline fun <reified T> register(binder: ItemViewBinder<T, *>) {
        register(T::class.java, binder)
    }

    fun <T> register(clazz: Class<T>, binder: ItemViewBinder<T, *>) {
        helper.register(clazz, binder)
    }

    override fun getItemViewType(position: Int): Int {
        return helper.geiItemViewType(getItem(position).javaClass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return helper.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        helper.onBindViewHolder(holder, getItem(position))
    }
}