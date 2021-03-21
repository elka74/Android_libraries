package com.example.android_libraries.mvp.presenter

import com.example.android_libraries.mvp.model.entity.GithubUser
import com.example.android_libraries.mvp.view.LoginView
import com.example.android_libraries.mvp.view.UsersView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class LoginPresenter(val router: Router, val user: GithubUser) :
        MvpPresenter<LoginView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLogin(user.login)
    }


    fun backClick(): Boolean {
        router.exit()
        return true
    }
}
