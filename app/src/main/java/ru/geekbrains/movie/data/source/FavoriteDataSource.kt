package ru.geekbrains.movie.data.source

import ru.geekbrains.movie.data.model.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface FavoriteDataSource {

    interface Local {
        fun getFavorites(): Flowable<List<Movie>>

        fun insertFavorites(movie: Movie): Completable

        fun deleteFavorites(movie: Movie): Completable

        fun isFavorite(movieId: Int): Single<Boolean>
    }
}
