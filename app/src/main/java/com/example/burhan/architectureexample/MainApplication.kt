package com.example.burhan.architectureexample

import android.app.Application
import com.example.burhan.architectureexample.di.ApplicationComponent
import com.example.burhan.architectureexample.di.DaggerApplicationComponent

class MainApplication: Application() {
    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
    }
}