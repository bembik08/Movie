package ru.geekbrains.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.geekbrains.movie.base.BaseAdapter
import ru.geekbrains.movie.base.BaseViewHolder
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.databinding.ItemPosterBinding

class PosterAdapter(
    private val listener: (Movie) -> Unit
) : BaseAdapter<Movie, ItemPosterBinding>(listener, {}) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemPosterBinding> =
        PosterHolder(
            ItemPosterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )

    class PosterHolder(
        private val itemPosterBinding: ItemPosterBinding,
        listener: (Movie) -> Unit
    ) : BaseViewHolder<Movie, ItemPosterBinding>(itemPosterBinding, listener) {
        override fun onBind(itemData: Movie) {
            super.onBind(itemData)
            itemPosterBinding.movie = itemData
        }
    }
}
