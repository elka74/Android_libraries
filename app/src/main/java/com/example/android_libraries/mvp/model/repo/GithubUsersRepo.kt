package com.example.android_libraries.mvp.model.repo

import com.example.android_libraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}