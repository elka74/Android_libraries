package com.example.android_libraries.ui.activity

import android.os.Bundle
import com.example.android_libraries.R
import com.example.android_libraries.databinding.ActivityMainBinding
import com.example.android_libraries.mvp.presenter.MainPresenter
import com.example.android_libraries.mvp.view.MainView
import com.example.android_libraries.ui.App
import com.example.android_libraries.ui.BackClickListener
import com.example.android_libraries.ui.adapter.UsersRVAdapter
import com.example.android_libraries.ui.navigation.AndroidScreens
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    val navigator = AppNavigator(this, R.id.container)

    private var ui: ActivityMainBinding? = null
    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, AndroidScreens())
    }

    private var adapter: UsersRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackClickListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}
