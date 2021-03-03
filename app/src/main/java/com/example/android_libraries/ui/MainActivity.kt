package com.example.android_libraries.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_libraries.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var ui:ActivityMainBinding? = null

    val counters = mutableListOf(0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui?.root)

        ui?.btnCounter1?.setOnClickListener {
            ui?.btnCounter1?.text = (++counters[0]).toString()
        }

        ui?.btnCounter2?.setOnClickListener {
            ui?.btnCounter2?.text = (++counters[1]).toString()
        }

        ui?.btnCounter3?.setOnClickListener {
            ui?.btnCounter3?.text = (++counters[2]).toString()
        }

        initView()
    }

    fun initView(){
        ui?.btnCounter1?.text = counters[0].toString()
        ui?.btnCounter2?.text = counters[1].toString()
        ui?.btnCounter3?.text = counters[2].toString()
    }
}