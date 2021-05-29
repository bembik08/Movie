package ru.geekbrains.movie.data.model

interface GeneralEntity {
    fun areItemsTheSame(newItem: GeneralEntity): Boolean
    fun areContentsTheSame(newItem: GeneralEntity): Boolean
}
