package ru.geekbrains.movie.ui.genres

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.geekbrains.movie.R
import ru.geekbrains.movie.base.BaseFragment
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.databinding.FragmentMovieTypeBinding
import ru.geekbrains.movie.ui.adapter.MoviesTypeAdapter
import ru.geekbrains.movie.utils.GenreUtil
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MovieTypeFragment : BaseFragment<FragmentMovieTypeBinding>() {

    override val layoutResource: Int
        get() = R.layout.fragment_movie_type

    override val viewModel by sharedViewModel<GenresViewModel>()

    private val args: MovieTypeFragmentArgs by navArgs()

    private val moviesTypeAdapter = MoviesTypeAdapter(::onItemClick) {
        viewModel.getMovies(GenreUtil.getIdGenre(args.title))
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = this@MovieTypeFragment
            titleFragment = args.title
            recyclerViewMoviesGenre.adapter = moviesTypeAdapter
            recyclerViewMoviesGenre.scrollToPosition(0)
            genreVM = viewModel
        }
        initListener()
    }

    private fun initListener() {
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onItemClick(movie: Movie) {
        val action = MovieTypeFragmentDirections.actionMovieTypeFragmentToDetailFragment(movie.id)
        findNavController().navigate(action)
    }
}
