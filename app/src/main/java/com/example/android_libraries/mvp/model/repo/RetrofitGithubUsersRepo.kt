package com.example.android_libraries.mvp.model.repo

import com.example.android_libraries.mvp.model.api.IDataSource
import com.example.android_libraries.mvp.model.cash.IGithubUsersCash
import com.example.android_libraries.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.schedulers.Schedulers


class RetrofitGithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cash: IGithubUsersCash
) : IGithubUsersRepo {


    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    cash.putUsers(users).toSingleDefault(users)
                }
        } else {
            cash.getUsers()
        }
    }.subscribeOn(Schedulers.io())
}