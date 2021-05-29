package ru.geekbrains.movie.di

import ru.geekbrains.movie.data.source.remote.MovieService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { get<Retrofit>().create(MovieService::class.java) }
}
