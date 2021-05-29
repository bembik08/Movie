package ru.geekbrains.movie.ui.actor

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.geekbrains.movie.R
import ru.geekbrains.movie.base.BaseFragment
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.databinding.FragmentMovieOfCompanyBinding
import ru.geekbrains.movie.ui.adapter.MoviesTypeAdapter
import ru.geekbrains.movie.ui.company.MovieOfCompanyViewModel
import kotlinx.android.synthetic.main.fragment_movie_of_company.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieOfCompanyFragment: BaseFragment<FragmentMovieOfCompanyBinding>() {

    override val layoutResource: Int
        get() = R.layout.fragment_movie_of_company

    override val viewModel by viewModel<MovieOfCompanyViewModel>()

    private val args: MovieOfCompanyFragmentArgs by navArgs()

    private val moviesTypeAdapter = MoviesTypeAdapter(::onItemClick) {
        viewModel.getData(args.idCompany)
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = this@MovieOfCompanyFragment
            nameCompany = args.nameCompany
            viewModel.getData(args.idCompany)
            movieOfCompanyVM = viewModel
            recyclerMoviesOfCompany.adapter = moviesTypeAdapter
            recyclerMoviesOfCompany.scrollToPosition(0)
        }
        initListener()
    }

    private fun initListener() {
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onItemClick(movie: Movie) {
        val action = MovieOfCompanyFragmentDirections.actionMovieOfCompanyFragmentToDetailFragment(movie.id)
        findNavController().navigate(action)
    }
}
