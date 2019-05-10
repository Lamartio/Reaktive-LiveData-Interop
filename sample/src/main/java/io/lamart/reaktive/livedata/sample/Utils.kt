package io.lamart.reaktive.livedata.sample

import android.content.Context
import android.os.Looper
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import java.util.logging.Handler


val mainHandler = android.os.Handler(Looper.getMainLooper())

inline fun <reified T : ViewModel> FragmentActivity.viewModel(vararg params: Any?): Lazy<T> = lazy {
    applicationContext
        .let { it as ViewModelFactory.Owner }
        .factory
        .withParams(params)
        .let { ViewModelProviders.of(this, it) }
        .get(T::class.java)
}

fun Context.toast(text: CharSequence) =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()