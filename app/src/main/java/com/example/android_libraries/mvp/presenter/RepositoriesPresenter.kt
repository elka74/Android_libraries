package com.example.android_libraries.mvp.presenter

import com.example.android_libraries.mvp.model.entity.GithubRepositories
import com.example.android_libraries.mvp.view.RepositoriesView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepositoriesPresenter (val router: Router, val githubRepository: GithubRepositories)
    : MvpPresenter<RepositoriesView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setId(githubRepository.id ?: "")
        viewState.setTitle(githubRepository.name ?: "")

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}