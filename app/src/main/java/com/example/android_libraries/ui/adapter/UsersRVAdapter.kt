package com.example.android_libraries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_libraries.databinding.ItemUserBinding
import com.example.android_libraries.mvp.presenter.list.IUserListPresenter
import com.example.android_libraries.mvp.view.list.IUserItemView

class UsersRVAdapter(val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })


    inner class ViewHolder(val ui: ItemUserBinding) : RecyclerView.ViewHolder(ui.root),
        IUserItemView {

        override fun setLogin(text: String) = with(ui) {
            tvLogin.text = text
        }

        override var pos = -1
    }
}