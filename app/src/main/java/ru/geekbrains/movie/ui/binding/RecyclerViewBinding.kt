package ru.geekbrains.movie.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.movie.base.BindDataAdapter

@Suppress("UNCHECKED_CAST")
@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: List<T>?) {
    (recyclerView.adapter as? BindDataAdapter<List<T>>)?.setData(data)
}
