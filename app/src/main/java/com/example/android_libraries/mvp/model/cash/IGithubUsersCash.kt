package com.example.android_libraries.mvp.model.cash

import com.example.android_libraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCash {
    fun getUsers():Single<List<GithubUser>>
    fun putUsers(users:List<GithubUser>):Completable
}