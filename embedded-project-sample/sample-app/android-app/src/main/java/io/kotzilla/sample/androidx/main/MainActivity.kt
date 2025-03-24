package io.kotzilla.sample.androidx.main

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.kotzilla.sample.androidx.R
import io.kotzilla.sample.androidx.components.main.MyViewModel
import io.kotzilla.sample.androidx.components.main.RandomId
import io.kotzilla.sample.androidx.components.main.SERVICE_IMPL
import io.kotzilla.sample.androidx.components.main.ScopedComponent
import io.kotzilla.sample.androidx.components.main.SimpleService
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class MainActivity : AppCompatActivity(), AndroidScopeComponent {

    override val scope: Scope by activityScope()

    // Inject by Interface - default definition
    val service: SimpleService by inject()
    // Inject factory
    val randomId: RandomId by inject()
    val vm : MyViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.main_activity)
        title = "Android App"

        findViewById<Button>(R.id.main_button).setOnClickListener {
            get<RandomId>()
        }

        assert(SERVICE_IMPL == service.id)
        assert(SERVICE_IMPL == vm.simpleService.id)
        assert(get<RandomId>().id != randomId.id)

//        KotzillaSDK.trace("scope - get", stacktrace = true){
//            scope.get<ScopedComponent>().let {
//                KotzillaSDK.log("got scoped component -> $it")
//            }
//        }
    }
}