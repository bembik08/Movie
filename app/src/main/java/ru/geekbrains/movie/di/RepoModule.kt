package ru.geekbrains.movie.di

import androidx.room.Room
import ru.geekbrains.movie.data.repository.FavoriteRepository
import ru.geekbrains.movie.data.repository.FavoriteRepositoryType
import ru.geekbrains.movie.data.repository.MovieRepository
import ru.geekbrains.movie.data.repository.MovieRepositoryType
import ru.geekbrains.movie.data.source.FavoriteDataSource
import ru.geekbrains.movie.data.source.MovieDataSource
import ru.geekbrains.movie.data.source.local.FavoriteLocalDataSource
import ru.geekbrains.movie.data.source.local.database.AppDatabase
import ru.geekbrains.movie.data.source.local.database.DatabaseConfig.DATABASE_NAME
import ru.geekbrains.movie.data.source.remote.MovieRemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    single { get<AppDatabase>().movieDao() }
}

val favoriteRepoModule = module {
    single<FavoriteDataSource.Local> { FavoriteLocalDataSource(get()) }

    single<FavoriteRepository> { FavoriteRepositoryType(get()) }
}

val movieRepoModule = module {
    single<MovieDataSource.Remote> { MovieRemoteDataSource(get()) }

    single<MovieRepository> { MovieRepositoryType(get()) }
}
