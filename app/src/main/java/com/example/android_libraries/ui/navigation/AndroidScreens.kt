package com.example.android_libraries.ui.navigation

import com.example.android_libraries.mvp.model.entity.GithubRepositories
import com.example.android_libraries.mvp.model.entity.GithubUser
import com.example.android_libraries.mvp.navigation.IScreens
import com.example.android_libraries.ui.fragment.RepositoriesFragment
import com.example.android_libraries.ui.fragment.UserFragment
import com.example.android_libraries.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
        override fun users() = FragmentScreen { UsersFragment.newInstance() }
        override fun user(githubUser: GithubUser) =
                FragmentScreen { UserFragment.newInstance(githubUser) }

        override fun repositories(githubRepositories: GithubRepositories) =
                FragmentScreen { RepositoriesFragment.newInstance(githubRepositories) }
}
