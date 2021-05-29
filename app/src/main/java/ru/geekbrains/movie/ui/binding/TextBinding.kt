package ru.geekbrains.movie.ui.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ru.geekbrains.movie.data.model.Genre

@BindingAdapter("genre")
fun loadText(view: TextView, list: List<Genre>) {}
