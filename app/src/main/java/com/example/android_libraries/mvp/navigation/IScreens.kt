package com.example.android_libraries.mvp.navigation

import com.example.android_libraries.mvp.model.entity.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(githubUser: GithubUser):Screen
}