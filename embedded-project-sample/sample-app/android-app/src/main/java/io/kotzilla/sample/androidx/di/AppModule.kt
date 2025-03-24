package io.kotzilla.sample.androidx.di

import io.kotzilla.sample.androidx.components.main.MyViewModel
import io.kotzilla.sample.androidx.components.main.RandomId
import io.kotzilla.sample.androidx.components.main.ScopedComponent
import io.kotzilla.sample.androidx.components.main.SimpleService
import io.kotzilla.sample.androidx.components.main.SimpleServiceImpl
import io.kotzilla.sample.androidx.main.MainActivity
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.*
import org.koin.dsl.*
import java.util.UUID

val appModule = module {
    singleOf(::SimpleServiceImpl) { bind<SimpleService>() }
    factory { RandomId() }
    viewModelOf(::MyViewModel)
    scope<MainActivity> {
        scoped { ScopedComponent(UUID.randomUUID().toString()) }
    }
}

val allModules = module {
    includes(appModule)
}