package com.example.android_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_libraries.databinding.FragmentUserBinding
import com.example.android_libraries.mvp.model.api.ApiHolder
import com.example.android_libraries.mvp.model.cash.room.RoomGithubRepositoriesCash
import com.example.android_libraries.mvp.model.entity.GithubUser
import com.example.android_libraries.mvp.model.entity.room.db.Database
import com.example.android_libraries.mvp.model.repo.RetrofitGithubRepositoriesRepo
import com.example.android_libraries.mvp.presenter.UserPresenter
import com.example.android_libraries.mvp.view.UserView
import com.example.android_libraries.ui.App
import com.example.android_libraries.ui.BackButtonListener
import com.example.android_libraries.ui.adapter.RepositoriesRVAdapter
import com.example.android_libraries.ui.navigation.AndroidScreens
import com.example.android_libraries.ui.network.AndroidNetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    companion object {
        private const val USER_ARG = "user"

        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_ARG, user)
            }
        }
    }

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER_ARG) as GithubUser
        UserPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubRepositoriesRepo(
                ApiHolder.api,
                AndroidNetworkStatus(App.instance),
                RoomGithubRepositoriesCash(Database.getInstance())
            ),
            App.instance.router,
            user,
            AndroidScreens()
        )
    }

    private var vb: FragmentUserBinding? = null

    var adapter: RepositoriesRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUserBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvRepos?.layoutManager = LinearLayoutManager(context)
        adapter = RepositoriesRVAdapter(presenter.repositoriesListPresenter)
        vb?.rvRepos?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }


    override fun backPressed() = presenter.backPressed()
}