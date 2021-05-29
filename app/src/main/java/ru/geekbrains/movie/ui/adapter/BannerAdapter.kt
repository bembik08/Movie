package ru.geekbrains.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.geekbrains.movie.base.BaseAdapter
import ru.geekbrains.movie.base.BaseViewHolder
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.databinding.ItemBannerBinding

class BannerAdapter(
    private val listener: (Movie) -> Unit
) : BaseAdapter<Movie, ItemBannerBinding>(listener, {}) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemBannerBinding> =
        PosterHolder(
            ItemBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )

    class PosterHolder(
        private val itemBannerBinding: ItemBannerBinding,
        listener: (Movie) -> Unit
    ) : BaseViewHolder<Movie, ItemBannerBinding>(itemBannerBinding, listener) {
        override fun onBind(itemData: Movie) {
            super.onBind(itemData)
            itemBannerBinding.movie = itemData
        }
    }
}
