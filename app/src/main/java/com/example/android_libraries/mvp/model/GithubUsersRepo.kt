package com.example.android_libraries.mvp.model

import com.example.android_libraries.mvp.model.entity.GithubUser

class GithubUsersRepo {
    private val users = listOf<GithubUser>(
       GithubUser("login1"),
       GithubUser("login2"),
       GithubUser("login3"),
       GithubUser("login4"),
       GithubUser("login5"),
       GithubUser("login6"),
    )

    fun getUsers(): List<GithubUser> {
        return users
    }
}