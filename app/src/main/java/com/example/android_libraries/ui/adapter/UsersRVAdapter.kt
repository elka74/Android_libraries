package com.example.android_libraries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_libraries.databinding.ItemUserBinding
import com.example.android_libraries.mvp.model.image.IImageLoader
import com.example.android_libraries.mvp.presenter.list.IUserListPresenter
import com.example.android_libraries.mvp.view.list.IUserItemView

class UsersRVAdapter(val presenter: IUserListPresenter, val imageLoader: IImageLoader<ImageView>) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemUserBinding) : RecyclerView.ViewHolder(vb.root), IUserItemView {
        override var pos = -1

        override fun setLogin(text: String) = with(vb) {
            tvLogin.text = text
        }

        override fun loadAvatar(url: String)  = with(vb) {
            imageLoader.load(url, ivAvatar)
        }
    }
}
