package ru.geekbrains.movie.di

import ru.geekbrains.movie.ui.actor.MovieOfActorViewModel
import ru.geekbrains.movie.ui.company.MovieOfCompanyViewModel
import ru.geekbrains.movie.ui.detail.DetailViewModel
import ru.geekbrains.movie.ui.favorite.FavoriteViewModel
import ru.geekbrains.movie.ui.genres.GenresViewModel
import ru.geekbrains.movie.ui.home.HomeViewModel
import ru.geekbrains.movie.ui.search.SearchViewModel
import ru.geekbrains.movie.ui.seeall.SeeAllViewModel
import ru.geekbrains.movie.ui.upcoming.UpComingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GenresViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { UpComingViewModel(get(),get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { MovieOfActorViewModel(get()) }
    viewModel { MovieOfCompanyViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { SeeAllViewModel(get()) }
}
