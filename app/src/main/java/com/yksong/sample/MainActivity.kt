package com.yksong.sample

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import com.yksong.sample.model.Company
import com.yksong.sample.model.Person
import com.yksong.sample.viewbinder.CompanyItemViewBinder
import com.yksong.sample.viewbinder.PersonItemViewBinder
import com.yksong.simpletype.SimpleTypedListAdapter

class MainActivity : Activity() {

    private val adapter = SimpleTypedListAdapter()

    private val list1 = listOf(
        Person("P1"),
        Company("C1"),
        Person("P2"),
        Company("C2"),
        Person("P3")
    )

    private val list2 = listOf(
        Company("C1"),
        Person("P1"),
        Company("C2"),
        Person("P2"),
        Person("P4")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_layout)

        findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter

        adapter.register(PersonItemViewBinder())
        adapter.register(CompanyItemViewBinder())

        adapter.submitList(list1)

        Handler().postDelayed({
            adapter.submitList(list2)
        }, 2000)
    }
}