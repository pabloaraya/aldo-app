package cl.blackmind.aldoapp

import android.app.Application
import cl.blackmind.aldoapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class AldoApplication : Application() {

    override fun onCreate() {
        initKoin()
        super.onCreate()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@AldoApplication)
            modules(
                listOf(
                    viewModelModule,
                    dataModule,
                    domainModule,
                    networkModule,
                    appModule
                )
            )
        }
    }
}