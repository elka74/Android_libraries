package com.example.android_libraries.mvp.model.cash.room

import com.example.android_libraries.mvp.model.cash.IGithubUsersCash
import com.example.android_libraries.mvp.model.entity.GithubUser
import com.example.android_libraries.mvp.model.entity.room.RoomGithubUser
import com.example.android_libraries.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubUsersCash(val db:Database):IGithubUsersCash {
    override fun getUsers() = Single.fromCallable {
        db.userDao.getAll().map {user->
            GithubUser(user.id,user.login,user.avatarUrl,user.reposUrl )
        }
    }

    override fun putUsers(users: List<GithubUser>) = Completable.fromAction{
        val roomUsers = users.map {user->
            RoomGithubUser(user.id, user.login, user.avatarUrl, user.reposUrl)}
        db.userDao.insert(roomUsers)

        }.subscribeOn(Schedulers.io())
    }

