package ru.geekbrains.movie.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.geekbrains.movie.base.RxViewModel
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.data.repository.FavoriteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class FavoriteViewModel (
    private val favoriteRepository: FavoriteRepository
) : RxViewModel() {

    private val _favorite = MutableLiveData<List<Movie>>()
    val favorite: LiveData<List<Movie>>
        get() = _favorite

    init {
        getFavorite()
    }

    private fun getFavorite() {
        favoriteRepository.getFavorite()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _favorite.value = it
                },
                { error.value = it.message }
            )
            .addTo(disposables)
    }

    fun deleteFavorite(movie: Movie) {
        favoriteRepository.deleteFavorite(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {},
                { error.value = it.message }
            )
            .addTo(disposables)
    }
}
