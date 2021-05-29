package ru.geekbrains.movie.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.geekbrains.movie.base.BaseAdapter
import ru.geekbrains.movie.base.BaseViewHolder
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.databinding.ItemUpcomingBinding

class UpComingAdapter (
    private val onDetailClick: (View,Movie) -> Unit,
    private val onPlayClick: (View,Movie) -> Unit,
    loadMore: () -> Unit
) :
    BaseAdapter<Movie, ItemUpcomingBinding>({}, loadMore) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemUpcomingBinding> {
        val itemView = ItemUpcomingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UpcomingHolder(itemView,onDetailClick,onPlayClick)
    }

    class UpcomingHolder(
        private val itemMoviePosterBinding: ItemUpcomingBinding,
        private val onDetailClick: (View,Movie) -> Unit,
        private val onPlayClick: (View,Movie) -> Unit
    ) : BaseViewHolder<Movie, ItemUpcomingBinding>(itemMoviePosterBinding,{}) {
        override fun onBind(itemData: Movie) {
            super.onBind(itemData)
            itemMoviePosterBinding.movie = itemData
            itemMoviePosterBinding.imageViewDetail.setOnClickListener {
                onDetailClick(it,itemData)
            }
            itemMoviePosterBinding.imagePlayYoutube.setOnClickListener {
                onPlayClick(it,itemData)
            }
        }
    }
}
