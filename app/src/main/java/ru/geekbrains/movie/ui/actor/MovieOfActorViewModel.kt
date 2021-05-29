package ru.geekbrains.movie.ui.actor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.geekbrains.movie.base.RxViewModel
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.data.repository.MovieRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieOfActorViewModel(
    private val movieRepository: MovieRepository
) : RxViewModel() {

    private val _movie = MutableLiveData<MutableList<Movie>>()
    val movies: LiveData<MutableList<Movie>>
        get() = _movie

    fun getData(actorId: Int) {
        movieRepository.getMoviesOfActor(actorId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _movie.value = it as MutableList<Movie>
                },
                {
                    error.value = it.message
                }
            )
            .addTo(disposables)
    }
}
