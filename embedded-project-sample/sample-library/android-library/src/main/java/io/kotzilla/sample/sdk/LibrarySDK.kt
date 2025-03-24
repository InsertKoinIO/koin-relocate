package io.kotzilla.sample.sdk

import android.content.Context
import embedded.koin.android.ext.koin.androidContext
import embedded.koin.core.Koin
import embedded.koin.dsl.koinApplication
import embedded.koin.dsl.module
import java.util.UUID

const val SDK_VERSION = "1.0"

class IdGenerator(){
    fun getId() : String = UUID.randomUUID().toString()
}

object LibrarySDK {

    private var koin: Koin? = null
    private val idGen by lazy { koin!!.get<IdGenerator>() }
    private val ctx by lazy { koin!!.get<Context>() }
    const val TAG : String = "[SAMPLE-SDK]"

    fun setup(context : Context){
        koin = koinApplication {
            modules(module {
                factory { IdGenerator() }
            })
            androidContext(context)
        }.koin
    }

    fun start(){
        if (koin != null){
            println("$TAG $SDK_VERSION - start")
            println("$TAG $SDK_VERSION - id: ${idGen.getId()}")
            println("$TAG $SDK_VERSION - ctx: $ctx")
        } else error("call setup(context) before")
    }

    fun stop(){
        println("$TAG $SDK_VERSION - stop")
    }
}