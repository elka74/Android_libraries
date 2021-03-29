package com.example.android_libraries.mvp.model.repo

import com.example.android_libraries.mvp.model.api.IDataSource
import io.reactivex.rxjava3.schedulers.Schedulers


class RetrofitGithubUsersRepo(val api: IDataSource): IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())

}