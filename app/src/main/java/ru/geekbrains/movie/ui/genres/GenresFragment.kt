package ru.geekbrains.movie.ui.genres

import androidx.navigation.fragment.findNavController
import ru.geekbrains.movie.R
import ru.geekbrains.movie.base.BaseFragment
import ru.geekbrains.movie.data.model.Genre
import ru.geekbrains.movie.databinding.FragmentGenreBinding
import ru.geekbrains.movie.ui.adapter.GenresAdapter
import ru.geekbrains.movie.utils.make
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GenresFragment : BaseFragment<FragmentGenreBinding>() {

    override val layoutResource: Int
        get() = R.layout.fragment_genre

    override val viewModel by sharedViewModel<GenresViewModel>()

    private var isConnection = false

    private val genresAdapter = GenresAdapter(::onItemGenreClick)

    override fun initData() {
        isConnection = NetworkUtil.isConnection(requireContext())
        if (!isConnection) {
            view?.make(getString(R.string.err_internet))
            return
        }
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            genresVM = viewModel
            recyclerGenres.adapter = genresAdapter
        }
    }

    private fun onItemGenreClick(itemGenre: Genre) {
        binding.apply {
            viewModel.getMovies(itemGenre.id)
            navigateToList(itemGenre.name)
        }
    }

    private fun navigateToList(title: String) {
        val action = GenresFragmentDirections.actionGenresFragmentToMovieTypeFragment(title)
        findNavController().navigate(action)
    }
}
