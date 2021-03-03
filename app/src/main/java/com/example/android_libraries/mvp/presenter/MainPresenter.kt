package com.example.android_libraries.mvp.presenter

import com.example.android_libraries.mvp.model.CountersModel
import com.example.android_libraries.mvp.view.MainView

class MainPresenter(val mainView: MainView) {
    val model = CountersModel()

    fun counterClick1() {
        val nextValue = model.next(0)
        mainView.setButton1Text(nextValue.toString())
    }

    fun counterClick2() {
        val nextValue1 = model.next(1)
        mainView.setButton2Text(nextValue1.toString())
    }

    fun counterClick3() {
        val nextValue2 = model.next(2)
        mainView.setButton3Text(nextValue2.toString())
    }
}
