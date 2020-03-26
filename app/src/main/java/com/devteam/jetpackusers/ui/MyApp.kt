package com.devteam.jetpackusers.ui

import android.app.Application
import com.devteam.jetpackusers.common.AppViewModelFactory
import com.devteam.jetpackusers.io.RetrofitInstance
import com.devteam.jetpackusers.repository.DataRepository
import com.devteam.jetpackusers.utils.Logger
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApp : Application(), KodeinAware {

    override val kodein: Kodein = Kodein {

        bind<DataRepository>() with provider {
            DataRepository(RetrofitInstance.retroService)
        }

        bind<AppViewModelFactory>() with singleton { AppViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        val k = kodein
        Logger.d("*** $k")
    }
}