package com.example.android_libraries.mvp.model.repo

import com.example.android_libraries.mvp.model.api.IDataSource
import com.example.android_libraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.schedulers.Schedulers


class RetrofitGithubRepositoriesRepo(val api: IDataSource): IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) = api.getUserRepositories(user.reposUrl)
        .subscribeOn(Schedulers.io())

}
