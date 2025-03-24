import android.app.Activity
import android.content.Context
import androidx.lifecycle.SavedStateHandle
import org.junit.Test
import io.kotzilla.sample.androidx.di.allModules
import org.koin.android.test.verify.androidVerify
import org.koin.test.verify.verify

class CheckModulesTest {

    @Test
    fun `Verify Configuration`() {
        allModules.androidVerify(
            extraTypes = listOf(
                String::class, // for Injected Id
//                Context::class,
//                Activity::class,
//                SavedStateHandle::class
            )
        )
    }
}