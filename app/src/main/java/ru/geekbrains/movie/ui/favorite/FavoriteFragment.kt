package ru.geekbrains.movie.ui.favorite

import android.view.View
import ru.geekbrains.movie.R
import ru.geekbrains.movie.base.BaseFragment
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.databinding.FragmentFavoriteBinding
import ru.geekbrains.movie.ui.adapter.FavoriteAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import androidx.navigation.fragment.findNavController

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(){

    override val layoutResource: Int
        get() = R.layout.fragment_favorite

    override val viewModel by sharedViewModel<FavoriteViewModel>()

    private val favoriteAdapter = FavoriteAdapter(::onItemClick, ::onItemDelete)

    override fun initData() {
        binding.apply {
            lifecycleOwner = this@FavoriteFragment
            favoriteVM = viewModel
            recyclerFavorite.adapter = favoriteAdapter
            initListener()
        }
    }

    private fun initListener(){
        binding.toolbarFavorite.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onItemDelete(view: View, movie: Movie) {
        if (view.id == R.id.textDeleteFavorite) {
            viewModel.deleteFavorite(movie)
        }
    }

    private fun onItemClick(movie: Movie) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(movie.id)
        findNavController().navigate(action)
    }
}
