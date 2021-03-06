package ru.geekbrains.movie.ui.search

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import ru.geekbrains.movie.R
import ru.geekbrains.movie.base.BaseFragment
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.databinding.FragmentSearchBinding
import ru.geekbrains.movie.ui.adapter.MoviesTypeAdapter
import ru.geekbrains.movie.utils.hideSoftInput
import ru.geekbrains.movie.utils.showSoftInput
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class SearchFragment : BaseFragment<FragmentSearchBinding>(),
    View.OnClickListener {

    override val layoutResource: Int
        get() = R.layout.fragment_search

    override val viewModel by viewModel<SearchViewModel>()

    private var disposeable: Disposable? = null

    private val searchAdapter = MoviesTypeAdapter(::onItemSearchClick) {}

    override fun initData() {
        binding.searchView.requestFocus()
        val imm: InputMethodManager? =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        binding.apply {
            lifecycleOwner = this@SearchFragment
            searchVM = viewModel
            recyclerResultSearch.adapter = searchAdapter
        }
        searchView.setOnSearchClickListener(this)
        initListener()
    }

    private fun onItemSearchClick(item: Movie) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(item.id)
        findNavController().navigate(action)
    }

    private fun initListener() {
        disposeable = SearchRX.fromSearchView(binding.searchView) {
            hideKeyboard()
        }
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewModel.search(it)
            }
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeable?.dispose()
        hideKeyboard()
    }

    private fun hideKeyboard() {
        activity?.let {
            hideSoftInput(it)
        }
    }

    private fun showKeyboard() {
        showSoftInput(searchView)
        disposeable = SearchRX.fromSearchView(binding.searchView) {
            hideKeyboard()
        }
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                viewModel.search(it)
            }
    }

    override fun onClick(v: View?) {
        showKeyboard()
    }
}
