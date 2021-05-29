package ru.geekbrains.movie.data.source.local

import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.data.source.FavoriteDataSource
import ru.geekbrains.movie.data.source.local.database.dao.MovieDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

class FavoriteLocalDataSource(
    private val movieDao: MovieDao
) : FavoriteDataSource.Local {

    override fun getFavorites(): Flowable<List<Movie>> =
        movieDao.getFavorites()

    override fun insertFavorites(movie: Movie): Completable =
        movieDao.insertMovie(movie)

    override fun deleteFavorites(movie: Movie): Completable =
        movieDao.delete(movie)

    override fun isFavorite(movieId: Int): Single<Boolean> =
        movieDao.isFavorite(movieId).map { it.isNotEmpty() }
}
