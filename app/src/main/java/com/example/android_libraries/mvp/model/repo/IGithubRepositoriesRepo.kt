package com.example.android_libraries.mvp.model.repo

import com.example.android_libraries.mvp.model.entity.GithubRepositories
import com.example.android_libraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesRepo {
    fun getRepositories(user: GithubUser): Single<List<GithubRepositories>>

}