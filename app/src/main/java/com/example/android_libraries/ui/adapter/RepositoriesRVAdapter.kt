package com.example.android_libraries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_libraries.databinding.ItemRepositoriesBinding
import com.example.android_libraries.mvp.presenter.list.IRepositoriesListPresenter
import com.example.android_libraries.mvp.view.list.IRepositoryItemView

class RepositoriesRVAdapter(val presenter: IRepositoriesListPresenter) :
    RecyclerView.Adapter<RepositoriesRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemRepositoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemRepositoriesBinding) : RecyclerView.ViewHolder(vb.root), IRepositoryItemView {
        override fun setName(name: String) = with(vb) {
            tvName.text = name
        }

        override var pos = -1
    }
}
