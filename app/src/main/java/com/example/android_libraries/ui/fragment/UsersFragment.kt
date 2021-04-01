package com.example.android_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_libraries.databinding.FragmentUsersBinding
import com.example.android_libraries.mvp.model.api.ApiHolder
import com.example.android_libraries.mvp.model.entity.room.db.Database
import com.example.android_libraries.mvp.model.repo.RetrofitGithubUsersRepo
import com.example.android_libraries.mvp.presenter.UsersPresenter
import com.example.android_libraries.mvp.view.UsersView
import com.example.android_libraries.ui.App
import com.example.android_libraries.ui.BackButtonListener
import com.example.android_libraries.ui.adapter.UsersRVAdapter
import com.example.android_libraries.ui.image.GlideImageLoader
import com.example.android_libraries.ui.navigation.AndroidScreens
import com.example.android_libraries.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(
                ApiHolder.api,
                AndroidNetworkStatus(App.instance),
                Database.getInstance()
            ),
            App.instance.router, AndroidScreens(),
        )
    }

    var adapter: UsersRVAdapter? = null

    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        vb?.rvUsers?.adapter = adapter

    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

}