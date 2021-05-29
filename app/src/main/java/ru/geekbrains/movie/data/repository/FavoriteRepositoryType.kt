package ru.geekbrains.movie.data.repository

import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.data.source.FavoriteDataSource
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

class FavoriteRepositoryType(private val local: FavoriteDataSource.Local) :
    FavoriteRepository {

    override fun getFavorite(): Flowable<List<Movie>> =
        local.getFavorites()

    override fun insertFavorite(movie: Movie): Completable =
        local.insertFavorites(movie)

    override fun deleteFavorite(movie: Movie): Completable =
        local.deleteFavorites(movie)

    override fun isFavorite(movieId: Int): Single<Boolean> =
        local.isFavorite(movieId)
}
