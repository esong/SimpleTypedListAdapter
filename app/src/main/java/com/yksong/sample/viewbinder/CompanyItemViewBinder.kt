package com.yksong.sample.viewbinder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yksong.sample.R
import com.yksong.sample.model.Company
import com.yksong.simpletype.ItemViewBinder

class CompanyItemViewBinder : ItemViewBinder<Company, CompanyItemViewBinder.CompanyViewHolder>() {
    override val layoutId: Int
        get() = R.layout.item_company

    class CompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(R.id.text)

        fun bind(item: Company) {
            textView.text = item.name
        }
    }

    override fun onCreateViewHolder(view: View): CompanyViewHolder {
        return CompanyViewHolder(view)
    }

    override fun onBindViewHolder(holder: CompanyViewHolder, item: Company) {
        holder.bind(item)
    }

}