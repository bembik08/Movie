package ru.geekbrains.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.geekbrains.movie.base.BaseAdapter
import ru.geekbrains.movie.base.BaseViewHolder
import ru.geekbrains.movie.data.model.Company
import ru.geekbrains.movie.databinding.ItemCompanyBinding

class CompanyAdapter(
    private val listener: (Company) -> Unit
) : BaseAdapter<Company, ItemCompanyBinding>(listener, {}) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Company, ItemCompanyBinding> =
        CompanyHolder(
            ItemCompanyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    class CompanyHolder(
        private val itemCompanyBinding: ItemCompanyBinding,
        listener: (Company) -> Unit
    ) : BaseViewHolder<Company, ItemCompanyBinding>(itemCompanyBinding, listener) {
        override fun onBind(itemData: Company) {
            super.onBind(itemData)
            itemCompanyBinding.company = itemData
        }
    }
}
