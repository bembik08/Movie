package ru.geekbrains.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.geekbrains.movie.base.BaseAdapter
import ru.geekbrains.movie.base.BaseViewHolder
import ru.geekbrains.movie.data.model.Genre
import ru.geekbrains.movie.databinding.ItemGenreBinding

class GenresAdapter(
    private val listener: (Genre) -> Unit
) : BaseAdapter<Genre, ItemGenreBinding>(listener, {}) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Genre, ItemGenreBinding> =
        GenresHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    class GenresHolder(
        private val itemGenreBinding: ItemGenreBinding,
        listener: (Genre) -> Unit
    ) : BaseViewHolder<Genre, ItemGenreBinding>(itemGenreBinding, listener) {
        override fun onBind(itemData: Genre) {
            super.onBind(itemData)
            itemGenreBinding.genre = itemData
        }
    }
}
