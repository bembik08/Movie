package ru.geekbrains.movie.ui.actor

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.geekbrains.movie.R
import ru.geekbrains.movie.base.BaseFragment
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.databinding.FragmentMovieOfActorBinding
import ru.geekbrains.movie.ui.adapter.MoviesTypeAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieOfActorFragment: BaseFragment<FragmentMovieOfActorBinding>() {

    override val layoutResource: Int
        get() = R.layout.fragment_movie_of_actor

    override val viewModel by viewModel<MovieOfActorViewModel>()

    private val args: MovieOfActorFragmentArgs by navArgs()

    private val moviesTypeAdapter = MoviesTypeAdapter(::onItemClick) {}

    override fun initData() {
        binding.apply {
            lifecycleOwner = this@MovieOfActorFragment
            nameActor = args.nameActor
            viewModel.getData(args.idActor)
            movieOfActorVM = viewModel
            recyclerMoviesOfActor.adapter = moviesTypeAdapter
            recyclerMoviesOfActor.scrollToPosition(0)
        }
        initListener()
    }

    private fun initListener() {
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onItemClick(movie: Movie) {
        val action = MovieOfActorFragmentDirections.actionMovieOfActorFragmentToDetailFragment(movie.id)
        findNavController().navigate(action)
    }
}
