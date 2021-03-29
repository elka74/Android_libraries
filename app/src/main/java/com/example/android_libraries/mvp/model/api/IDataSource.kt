package com.example.android_libraries.mvp.model.api

import com.example.android_libraries.mvp.model.entity.GithubRepositories
import com.example.android_libraries.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url


interface IDataSource {

    @GET("users")
    fun getUsers(@Header("Authorization") token: String) : Single<List<GithubUser>>


   @GET
   fun getUserRepositories(@Url url: String) : Single<List<GithubRepositories>>

}