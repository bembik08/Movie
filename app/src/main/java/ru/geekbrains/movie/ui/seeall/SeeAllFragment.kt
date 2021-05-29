package ru.geekbrains.movie.ui.seeall

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.geekbrains.movie.R
import ru.geekbrains.movie.base.BaseFragment
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.databinding.FragmentSeeAllBinding
import ru.geekbrains.movie.ui.adapter.MoviesTypeAdapter
import ru.geekbrains.movie.utils.GenreUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeeAllFragment : BaseFragment<FragmentSeeAllBinding>() {
    override val layoutResource: Int
        get() = R.layout.fragment_see_all
    override val viewModel by viewModel<SeeAllViewModel>()
    private val args: SeeAllFragmentArgs by navArgs()

    private val moviesTypeAdapter = MoviesTypeAdapter(::onItemClick) {
        viewModel.getData(args.typeMovie)
    }

    override fun initData() {
        binding.apply {
            lifecycleOwner = this@SeeAllFragment
            nameTitle = args.typeMovie
            viewModel.getData(args.typeMovie)
            seeAllVM = viewModel
            recyclerSeeAll.adapter = moviesTypeAdapter
        }
        initListener()
    }

    private fun initListener() {
        binding.materialToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onItemClick(movie: Movie) {
        val action = SeeAllFragmentDirections.actionSeeAllFragmentToDetailFragment(movie.id)
        findNavController().navigate(action)
    }
}
