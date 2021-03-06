package ru.geekbrains.movie.ui.seeall

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.geekbrains.movie.base.RxViewModel
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.data.model.MovieType
import ru.geekbrains.movie.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class SeeAllViewModel(
    private val movieRepository: MovieRepository
) : RxViewModel() {

    private var currentPage = 0
    private val _movie = MutableLiveData<MutableList<Movie>>()
    val movies: LiveData<MutableList<Movie>>
        get() = _movie

    fun getData(movieType: String) {
        movieRepository.getMoviesByType(getTypeMovie(movieType), ++currentPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (currentPage == 1) {
                        _movie.value = it as MutableList<Movie>
                    } else {
                        _movie.value?.addAll(it)
                    }
                },
                {
                    error.value = it.message
                    currentPage--
                }
            )
            .addTo(disposables)
    }

    private fun getTypeMovie(movieType: String): MovieType =
        when (movieType) {
            "Discovery" -> MovieType.Discovery
            "Trending" -> MovieType.Trending
            "TopRate" -> MovieType.TopRate
            "Popular" -> MovieType.Popular
            else -> MovieType.Discovery
        }
}
