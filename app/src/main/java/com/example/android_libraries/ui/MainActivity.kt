package com.example.android_libraries.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_libraries.databinding.ActivityMainBinding
import com.example.android_libraries.mvp.presenter.MainPresenter
import com.example.android_libraries.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private var ui: ActivityMainBinding? = null

    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui?.root)

        ui?.btnCounter1?.setOnClickListener {
            presenter.counterClick1()
        }
        ui?.btnCounter2?.setOnClickListener {
            presenter.counterClick2()
        }
        ui?.btnCounter3?.setOnClickListener {
            presenter.counterClick3()
        }
    }

    override fun setButton1Text(text: String) {
        ui?.btnCounter1?.text = text
    }

    override fun setButton2Text(text: String) {
        ui?.btnCounter2?.text = text
    }

    override fun setButton3Text(text: String) {
        ui?.btnCounter3?.text = text
    }
}
