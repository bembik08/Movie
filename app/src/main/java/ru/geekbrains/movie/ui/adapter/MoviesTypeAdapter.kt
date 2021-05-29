package ru.geekbrains.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.geekbrains.movie.base.BaseAdapter
import ru.geekbrains.movie.base.BaseViewHolder
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.databinding.ItemMovieBinding

class MoviesTypeAdapter(
    private val listener: (Movie) -> Unit,
    loadMore: () -> Unit
) : BaseAdapter<Movie, ItemMovieBinding>(listener, loadMore) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Movie, ItemMovieBinding> = MovieHolder(
        ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        listener
    )

    class MovieHolder(
        private val itemMovieBinding: ItemMovieBinding,
        listener: (Movie) -> Unit
    ) : BaseViewHolder<Movie, ItemMovieBinding>(itemMovieBinding, listener) {

        override fun onBind(itemData: Movie) {
            super.onBind(itemData)
            itemMovieBinding.movie = itemData
        }
    }
}


