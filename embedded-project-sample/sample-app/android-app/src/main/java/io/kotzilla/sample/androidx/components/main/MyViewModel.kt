package io.kotzilla.sample.androidx.components.main

import androidx.lifecycle.ViewModel

class MyViewModel(val simpleService: SimpleService) : ViewModel() {

    fun callService(): String {
        return simpleService.id
    }
}