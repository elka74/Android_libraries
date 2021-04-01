package com.example.android_libraries.mvp.model.repo

import com.example.android_libraries.mvp.model.api.IDataSource
import com.example.android_libraries.mvp.model.cash.IGithubRepositoriesCash
import com.example.android_libraries.mvp.model.entity.GithubUser
import com.example.android_libraries.mvp.model.entity.room.db.Database
import com.example.android_libraries.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.schedulers.Schedulers


class RetrofitGithubRepositoriesRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val cash: IGithubRepositoriesCash
) : IGithubRepositoriesRepo {

    override fun getRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.getRepositories(url)
                        .flatMap { repositories ->
                            cash.putRepositories(user, repositories)
                                .toSingleDefault(repositories)
                        }
                }
            } else {
                cash.getRepositories(user)
            }
        }.subscribeOn(Schedulers.io())
}

