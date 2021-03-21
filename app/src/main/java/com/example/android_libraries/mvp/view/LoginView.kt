package com.example.android_libraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleTagStrategy::class)
interface LoginView:MvpView {
    fun setLogin(text:String)

}