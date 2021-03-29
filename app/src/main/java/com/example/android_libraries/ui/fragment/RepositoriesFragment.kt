package com.example.android_libraries.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_libraries.databinding.FragmentRepositoriesBinding
import com.example.android_libraries.mvp.model.entity.GithubRepositories
import com.example.android_libraries.mvp.presenter.RepositoriesPresenter
import com.example.android_libraries.mvp.view.RepositoriesView
import com.example.android_libraries.ui.App
import com.example.android_libraries.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoriesFragment : MvpAppCompatFragment(), RepositoriesView, BackButtonListener {
    companion object {
        private const val REPOSITORY_ARG = "repository"

        fun newInstance(repository: GithubRepositories) = RepositoriesFragment().apply {
            arguments = Bundle().apply {
                putParcelable(REPOSITORY_ARG, repository)
            }
        }
    }

    private var vb: FragmentRepositoriesBinding? = null

    val presenter: RepositoriesPresenter by moxyPresenter {
        val repository = arguments?.getParcelable<GithubRepositories>(REPOSITORY_ARG) as GithubRepositories
        RepositoriesPresenter(App.instance.router, repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentRepositoriesBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun init() {}

    override fun setId(text: String) {
        vb?.tvId?.text = text
    }

    override fun setTitle(text: String) {
        vb?.tvTitle?.text = text
    }

    override fun backPressed() = presenter.backPressed()
}