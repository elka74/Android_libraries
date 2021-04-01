package com.example.android_libraries.mvp.presenter

import com.example.android_libraries.mvp.model.entity.GithubRepositories
import com.example.android_libraries.mvp.model.entity.GithubUser
import com.example.android_libraries.mvp.model.repo.IGithubRepositoriesRepo
import com.example.android_libraries.mvp.navigation.IScreens
import com.example.android_libraries.mvp.presenter.list.IRepositoriesListPresenter
import com.example.android_libraries.mvp.view.UserView
import com.example.android_libraries.mvp.view.list.IRepositoryItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UserPresenter(
    val uiScheduler: Scheduler, val repositoriesRepo: IGithubRepositoriesRepo,
    val router: Router, val user: GithubUser, val screens: IScreens
) : MvpPresenter<UserView>() {


    class RepositoriesListPresenter : IRepositoriesListPresenter {
        val repositories = mutableListOf<GithubRepositories>()
        override var itemClickListener: ((IRepositoryItemView) -> Unit)? = null
        override fun getCount() = repositories.size

        override fun bindView(view: IRepositoryItemView) {
            val repository = repositories[view.pos]
            repository.name?.let { view.setName(it) }
        }
    }

    val repositoriesListPresenter = RepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        repositoriesListPresenter.itemClickListener = { itemView ->
            val repositories = repositoriesListPresenter.repositories[itemView.pos]
            router.navigateTo(screens.repositories(repositories))
        }
    }


    fun loadData() {
        repositoriesRepo.getRepositories(user)
            .observeOn(uiScheduler)
            .subscribe({ repositories ->
                repositoriesListPresenter.repositories.clear()
                repositoriesListPresenter.repositories.addAll(repositories)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}