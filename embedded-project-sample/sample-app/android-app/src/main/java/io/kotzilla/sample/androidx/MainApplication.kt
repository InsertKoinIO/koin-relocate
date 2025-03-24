package io.kotzilla.sample.androidx

import android.app.Application
import io.kotzilla.sample.androidx.di.allModules
import io.kotzilla.sample.sdk.LibrarySDK
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Koin
        startKoin {
            androidContext(this@MainApplication)
            modules(allModules)
        }

        LibrarySDK.setup(this)
        LibrarySDK.start()
    }
}