package com.example.android_libraries.ui.navigation

import com.example.android_libraries.mvp.model.entity.GithubUser
import com.example.android_libraries.mvp.navigation.IScreens
import com.example.android_libraries.ui.fragment.LoginFragment
import com.example.android_libraries.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens{
        override fun users() = FragmentScreen { UsersFragment.newInstance() }
        override fun user(githubUser: GithubUser) = FragmentScreen{
                LoginFragment.newInstance(githubUser)}
}