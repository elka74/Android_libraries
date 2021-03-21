package com.example.android_libraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_libraries.databinding.FragmentUsersBinding
import com.example.android_libraries.databinding.LoginFragmentBinding
import com.example.android_libraries.mvp.model.GithubUsersRepo
import com.example.android_libraries.mvp.model.entity.GithubUser
import com.example.android_libraries.mvp.presenter.LoginPresenter
import com.example.android_libraries.mvp.presenter.UsersPresenter
import com.example.android_libraries.mvp.view.LoginView
import com.example.android_libraries.mvp.view.UsersView
import com.example.android_libraries.ui.App
import com.example.android_libraries.ui.BackClickListener
import com.example.android_libraries.ui.adapter.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class LoginFragment: MvpAppCompatFragment(), LoginView, BackClickListener {
    companion object {
        private const val US_ARG = "user"

        fun newInstance(user:GithubUser) = LoginFragment().apply {
            arguments = Bundle().apply {
                putParcelable(US_ARG, user)
            }
        }
    }

    val presenter: LoginPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(US_ARG) as GithubUser
        LoginPresenter(App.instance.router, user)

    }

    private var ui: LoginFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = LoginFragmentBinding.inflate(inflater, container, false).also {
        ui = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        ui = null
    }

    override fun setLogin(text:String) {
        ui?.txLogin?.text = text
    }

    override fun backPressed() = presenter.backClick()
}