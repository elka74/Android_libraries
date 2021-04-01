package com.example.android_libraries.mvp.model.cash.room

import com.example.android_libraries.mvp.model.cash.IGithubRepositoriesCash
import com.example.android_libraries.mvp.model.entity.GithubRepositories
import com.example.android_libraries.mvp.model.entity.GithubUser
import com.example.android_libraries.mvp.model.entity.room.RoomGithubRepository
import com.example.android_libraries.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

class RoomGithubRepositoriesCash(val db: Database) : IGithubRepositoriesCash {
    override fun getRepositories(user: GithubUser) = Single.fromCallable {
        val roomUser = db.userDao.findByLogin(user.login) ?: throw RuntimeException()
        return@fromCallable db.repositoryDao.findForUser(roomUser.id).map { user ->
            GithubRepositories(user.id, user.name)
        }
    }.subscribeOn(Schedulers.io())


    override fun putRepositories(
        user: GithubUser,
        repositories: List<GithubRepositories>
    ) = Completable.fromAction {
        val roomUser = db.userDao.findByLogin(user.login) ?: throw RuntimeException()
        val roomRepositories = repositories.map { repository ->
            RoomGithubRepository(repository.id, repository.name ?: "", roomUser.id)
        }
        db.repositoryDao.insert(roomRepositories)
    }.subscribeOn(Schedulers.io())

}