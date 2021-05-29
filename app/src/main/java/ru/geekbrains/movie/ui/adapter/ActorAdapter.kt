package ru.geekbrains.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.geekbrains.movie.base.BaseAdapter
import ru.geekbrains.movie.base.BaseViewHolder
import ru.geekbrains.movie.data.model.Actor
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.databinding.ItemActorBinding

class ActorAdapter(
    private val listener: (Actor) -> Unit
) : BaseAdapter<Actor, ItemActorBinding>(listener, {}) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Actor, ItemActorBinding> =
        ActorHolder(
            ItemActorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    class ActorHolder(
        private val itemActorBinding: ItemActorBinding,
        listener: (Actor) -> Unit
    ) : BaseViewHolder<Actor, ItemActorBinding>(itemActorBinding, listener) {
        override fun onBind(itemData: Actor) {
            super.onBind(itemData)
            itemActorBinding.actor = itemData
        }
    }
}
