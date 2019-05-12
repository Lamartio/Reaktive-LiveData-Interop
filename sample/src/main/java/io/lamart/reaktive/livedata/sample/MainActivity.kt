package io.lamart.reaktive.livedata.sample

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.badoo.reaktive.observable.subscribe
import io.lamart.reaktive.livedata.interop.toObservable


class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil
            .inflate<io.lamart.reaktive.livedata.sample.databinding.MainBinding>(
                layoutInflater,
                R.layout.main,
                null,
                false
            )
            .apply {
                lifecycleOwner = this@MainActivity
                model = viewModel
            }
            .root
            .let(::setContentView)

        viewModel.ticks
            .toObservable(this)
            .subscribe { toast("ticks: $it") }
    }

}

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