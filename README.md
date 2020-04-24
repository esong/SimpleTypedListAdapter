# SimpleTypedListAdapter
A simple RecyclerView ListAdapter implementation that supports different data types. 

This project is inspried by [@drakeet](https://github.com/drakeet)'s [MultiType](https://github.com/drakeet/MultiType) project. 

The main difference is that [MultiType](https://github.com/drakeet/MultiType) supports [RecyclerView.Adapter](https://github.com/drakeet/MultiType/blob/master/library/src/main/kotlin/com/drakeet/multitype/MultiTypeAdapter.kt#L43).
SimpleTypedListAdapter is a subclass to [ListAdapter](https://developer.android.com/reference/android/support/v7/recyclerview/extensions/ListAdapter). 
**As a result of that, all data classes passed in with [submitList()](https://developer.android.com/reference/android/support/v7/recyclerview/extensions/ListAdapter#submitList(java.util.List%3CT%3E)) need to have its custom `equals()` method implemented**

This project also simplied the implementation of [MultiType](https://github.com/drakeet/MultiType) down to [3 files](https://github.com/esong/SimpleTypedListAdapter/tree/master/library/src/main/java/com/yksong/simpletype). 

## Usage 
The usage of this Adapter is basically the same as [MultiType](https://github.com/drakeet/MultiType), but with some simplifications. 

1. Data class
```kotlin
data class Person(val name : String)
```

2. Create your ViewBinder class that overrides the base `ItemViewBinder`
```kotlin

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
```

3. Add the custom view binder to a SimpleTypedListAdapter

```kotlin
class MainActivity : Activity() {

    private val adapter = SimpleTypedListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_layout)

        findViewById<RecyclerView>(R.id.recycler_view).adapter = adapter

        adapter.register(PersonItemViewBinder())
        adapter.register(CompanyItemViewBinder())

        adapter.submitList(listOf(Person("P1")))
    }
}
```
