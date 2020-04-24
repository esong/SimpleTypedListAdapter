package com.yksong.sample.viewbinder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yksong.sample.R
import com.yksong.sample.model.Person
import com.yksong.simpletype.ItemViewBinder

class PersonItemViewBinder : ItemViewBinder<Person, PersonItemViewBinder.PersonViewHolder>() {
    override val layoutId: Int
        get() = R.layout.item_person

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.text)

        fun bind(item: Person) {
            textView.text = item.name
        }
    }

    override fun onCreateViewHolder(view: View): PersonViewHolder {
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, item: Person) {
        holder.bind(item)
    }

}