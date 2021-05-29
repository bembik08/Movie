package ru.geekbrains.movie.base

import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding
import ru.geekbrains.movie.data.model.GeneralEntity

abstract class BaseAdapter<T : GeneralEntity, B : ViewBinding>(
    private val listener: (T) -> Unit,
    private val loadMore: () -> Unit
) : ListAdapter<T, BaseViewHolder<T, B>>(BaseDiffUtil<T>()), BindDataAdapter<List<T>> {

    override fun onBindViewHolder(holder: BaseViewHolder<T, B>, position: Int) {
        holder.onBind(getItem(position))
        if ((position == currentList.size-1) || (position == (currentList.size+1) - currentList.size)) {
            loadMore()
        }
    }

    override fun setData(data: List<T>?) {
        submitList(data)
    }
}
