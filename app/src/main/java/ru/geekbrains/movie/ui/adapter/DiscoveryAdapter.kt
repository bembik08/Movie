package ru.geekbrains.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.geekbrains.movie.base.BaseAdapter
import ru.geekbrains.movie.base.BaseViewHolder
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.databinding.ItemDiscoveryBinding

class DiscoveryAdapter(private val listener: (Movie) -> Unit) :
    BaseAdapter<Movie, ItemDiscoveryBinding>(listener, {}) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemDiscoveryBinding> =
        DiscoveryHolder(
            ItemDiscoveryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), listener
        )

    class DiscoveryHolder(
        private val itemDiscoveryBinding: ItemDiscoveryBinding,
        listener: (Movie) -> Unit
    ) : BaseViewHolder<Movie, ItemDiscoveryBinding>(itemDiscoveryBinding, listener) {
        override fun onBind(itemData: Movie) {
            super.onBind(itemData)
            itemDiscoveryBinding.discovery = itemData
        }
    }
}
