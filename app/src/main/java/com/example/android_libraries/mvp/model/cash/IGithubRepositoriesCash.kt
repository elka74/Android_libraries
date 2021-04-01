package com.example.android_libraries.mvp.model.cash

import com.example.android_libraries.mvp.model.entity.GithubRepositories
import com.example.android_libraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCash {
    fun getRepositories(user: GithubUser): Single<List<GithubRepositories>>
    fun putRepositories(user: GithubUser, repositories: List<GithubRepositories>): Completable

}